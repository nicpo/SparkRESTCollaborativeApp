package com.np.spark.kernel;

import com.np.metastore.data.Analysis;
import com.np.metastore.kernel.AnalysisKernel;
import com.np.spark.data.SVMRequest;
import com.np.spark.data.SVMResponse;
import com.np.util.Config;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.mllib.classification.SVMModel;
import org.apache.spark.mllib.classification.SVMWithSGD;
import org.apache.spark.mllib.evaluation.BinaryClassificationMetrics;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors;
import org.apache.spark.mllib.optimization.L1Updater;
import org.apache.spark.mllib.optimization.SquaredL2Updater;
import org.apache.spark.mllib.regression.LabeledPoint;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.hive.HiveContext;
import org.apache.spark.sql.types.StructField;
import parquet.org.codehaus.jackson.map.ObjectMapper;
import parquet.org.codehaus.jackson.map.ObjectWriter;
import scala.Tuple2;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;

public class SVMKernel extends SparkKernel {

    public static void process(Analysis an, final SVMRequest lrr) {
        final HiveContext hiveContext = Config.getInstance().getHiveContext();

        try {
            DataFrame resultSet = hiveContext.sql(lrr.getQuery());

            JavaRDD<Row> rowRDD = resultSet.javaRDD();

            // Create RDD of LabeledPoint, skip wrong rows
            JavaRDD<LabeledPoint> parsedData = rowRDD.flatMap(
                    new FlatMapFunction<Row, LabeledPoint>() {
                        @Override
                        public Iterable<LabeledPoint> call(Row item) throws Exception {
                            ArrayList<LabeledPoint> ret = new ArrayList<LabeledPoint>(1);
                            try {
                                double[] points = new double[item.length() - 1];
                                for (int i = 1; i < item.length(); i++)
                                    points[i - 1] = Util.getAsDoubleExceptionUnhandled(item, i);

                                ret.add(new LabeledPoint(Util.getAsDoubleExceptionUnhandled(item, 0), Vectors.dense(points)));
                            } catch (NumberFormatException ex) {
                                // if any problems with row occurs - we just skip it
                                System.out.println("Skipping row " + item.toString());
                            }
                            return ret;
                        }
                    });


            // Split initial RDD into two, training/testing
            JavaRDD<LabeledPoint>[] splits = parsedData.randomSplit(new double[]{lrr.getTrainingSize(), 1 - lrr.getTrainingSize()}, System.currentTimeMillis());
            JavaRDD<LabeledPoint> training = splits[0].cache();
            JavaRDD<LabeledPoint> test = splits[1];

            // Building the model
            System.out.println("Building model: " + System.currentTimeMillis());

            final SVMWithSGD svm = new SVMWithSGD();
            svm.optimizer().setMiniBatchFraction(lrr.getMiniBatchFraction());
            svm.optimizer().setStepSize(lrr.getStepSize());
            if (lrr.getRegularizationType()!=null) {
                if (lrr.getRegularizationType().equals("L1")) {
                    svm.optimizer().setUpdater(new L1Updater());
                    svm.optimizer().setRegParam(lrr.getRegularizationParameter());
                } else if (lrr.getRegularizationType().equals("L2")) {
                    svm.optimizer().setUpdater(new SquaredL2Updater());
                    svm.optimizer().setRegParam(lrr.getRegularizationParameter());
                }
            }

            final SVMModel model = svm.train(training.rdd(), lrr.getIterations());

            // Clear the default threshold.
            // model.clearThreshold();

            // creating response
            SVMResponse response = new SVMResponse();

            // store model as byte array
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(stream);
            oos.writeObject(model);
            oos.close();
            stream.close();
            response.setModel(stream.toByteArray());

            response.setColumns(resultSet.columns());

            // Compute raw scores on the test set.
            JavaRDD<Tuple2<Object, Object>> scoreAndLabels = test.map(
                    new Function<LabeledPoint, Tuple2<Object, Object>>() {
                        public Tuple2<Object, Object> call(LabeledPoint p) {
                            Double score = model.predict(p.features());
                            return new Tuple2<Object, Object>(score, p.label());
                        }
                    }
            );

            // Get evaluation metrics.
            BinaryClassificationMetrics metrics = new BinaryClassificationMetrics(JavaRDD.toRDD(scoreAndLabels));
            response.setAuroc(metrics.areaUnderROC());
            response.setIntercept(model.intercept());

            // get features names and weights
            Vector weights = model.weights();
            double[] weightsArray = weights.toArray();

            StructField[] structField = rowRDD.first().schema().fields();
            for (int i = 1; i < structField.length; i++) {
                response.getWeights().put(structField[i].name(), weightsArray[i - 1]);
            }

            // serialize
            final ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String responseJSON = ow.writeValueAsString(response);
            System.out.println("*** Result: " + responseJSON);

            // Store into analysis
            an.setOutput(responseJSON);
            an.setState("DONE");
            an = AnalysisKernel.finish(an);
        } catch (Throwable ex) {
            an.setOutput(ex.getMessage());
            an.setState("ERROR");
            try {
                an = AnalysisKernel.finish(an);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
