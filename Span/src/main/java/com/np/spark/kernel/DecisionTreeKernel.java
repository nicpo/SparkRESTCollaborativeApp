package com.np.spark.kernel;

import com.np.metastore.data.Analysis;
import com.np.metastore.kernel.AnalysisKernel;
import com.np.spark.data.DecisionTreeRequest;
import com.np.spark.data.DecisionTreeResponse;
import com.np.util.Config;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.mllib.linalg.Vectors;
import org.apache.spark.mllib.regression.LabeledPoint;
import org.apache.spark.mllib.tree.DecisionTree;
import org.apache.spark.mllib.tree.model.DecisionTreeModel;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.hive.HiveContext;
import parquet.org.codehaus.jackson.map.ObjectMapper;
import parquet.org.codehaus.jackson.map.ObjectWriter;
import scala.Tuple2;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class DecisionTreeKernel extends SparkKernel {

    public static void process(Analysis an, final DecisionTreeRequest lrr) {
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

            // Train a DecisionTree model for classification.

            // to allow nulls in categoricalFeaturesInfo
            if (lrr.getCategoricalFeaturesInfo()==null)
                lrr.setCategoricalFeaturesInfo(new HashMap<Integer, Integer>());

            final DecisionTreeModel model;

            if (lrr.itsClassification())
                model = DecisionTree.trainClassifier(training, lrr.getNumClasses(), lrr.getCategoricalFeaturesInfo(), lrr.getImpurity(), lrr.getMaxDepth(), lrr.getMaxBins());
            else
                model = DecisionTree.trainRegressor(training, lrr.getCategoricalFeaturesInfo(), lrr.getImpurity(), lrr.getMaxDepth(), lrr.getMaxBins());


            // creating response
            DecisionTreeResponse response = new DecisionTreeResponse();

            // store model as byte array
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(stream);
            oos.writeObject(model);
            oos.close();
            stream.close();
            response.setModel(stream.toByteArray());

            response.setColumns(resultSet.columns());

            // Compute raw scores on the test set.
            JavaRDD<Tuple2<Double, Double>> scoreAndLabels = test.map(
                    new Function<LabeledPoint, Tuple2<Double, Double>>() {
                        public Tuple2<Double, Double> call(LabeledPoint p) {
                            Double score = model.predict(p.features());
                            return new Tuple2<Double, Double>(score, p.label());
                        }
                    }
            );

            if (lrr.itsClassification()) {
                Double testErr = 1.0 * scoreAndLabels.filter(new Function<Tuple2<Double, Double>, Boolean>() {
                    @Override
                    public Boolean call(Tuple2<Double, Double> pl) {
                        return !pl._1().equals(pl._2());
                    }
                }).count() / test.count();

                System.out.println("Test Error: " + testErr);
                response.setError(testErr);


            } else {
                Double testMSE =
                        scoreAndLabels.map(new Function<Tuple2<Double, Double>, Double>() {
                            @Override
                            public Double call(Tuple2<Double, Double> pl) {
                                Double diff = pl._1() - pl._2();
                                return diff * diff;
                            }
                        }).reduce(new Function2<Double, Double, Double>() {
                            @Override
                            public Double call(Double a, Double b) {
                                return a + b;
                            }
                        }) / parsedData.count();

                System.out.println("Test MSE: " + testMSE);
                response.setError(testMSE);

            }

            String reFormattedDecisionTree = Util.reFormatDecisionTree(model.toDebugString());
            System.out.println(reFormattedDecisionTree);
            response.setDebugTree(reFormattedDecisionTree);

            // serialize
            final ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String responseJSON = ow.writeValueAsString(response);
            System.out.println("*** Result: " + responseJSON);

            // Store into analysis
            an.setOutput(responseJSON);
            an.setState("DONE");
            an = AnalysisKernel.finish(an);
        } catch (Throwable ex) {
            ex.printStackTrace();
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
