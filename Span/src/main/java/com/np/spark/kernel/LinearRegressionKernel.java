package com.np.spark.kernel;

import com.np.metastore.data.Analysis;
import com.np.metastore.kernel.AnalysisKernel;
import com.np.spark.data.LinearRegressionRequest;
import com.np.spark.data.LinearRegressionResponse;
import com.np.util.Config;
import org.apache.spark.api.java.JavaDoubleRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors;
import org.apache.spark.mllib.regression.*;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.hive.HiveContext;
import org.apache.spark.sql.types.StructField;
import parquet.org.codehaus.jackson.map.ObjectMapper;
import parquet.org.codehaus.jackson.map.ObjectWriter;
import scala.Tuple2;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;

public class LinearRegressionKernel extends SparkKernel {

    public static void process(Analysis an, final LinearRegressionRequest lrr) {
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

            // Building the model
            final LinearRegressionWithSGD lr = new LinearRegressionWithSGD(0.000000001, lrr.getIterations(), 1.0);
            lr.setIntercept(true);
            final LinearRegressionModel model= lr.run(parsedData.rdd());

            // Evaluate model on training examples and compute training error
            JavaRDD<Tuple2<Double, Double>> valuesAndPreds = parsedData.map(
                    new Function<LabeledPoint, Tuple2<Double, Double>>() {
                        public Tuple2<Double, Double> call(LabeledPoint point) {
                            double prediction = model.predict(point.features());
                            return new Tuple2<Double, Double>(prediction, point.label());
                        }
                    }
            );

            double mse = new JavaDoubleRDD(valuesAndPreds.map(
                    new Function<Tuple2<Double, Double>, Object>() {
                        public Object call(Tuple2<Double, Double> pair) {
                            return Math.pow(pair._1() - pair._2(), 2.0);
                        }
                    }
            ).rdd()).mean();

            System.out.println("training Mean Squared Error = " + mse);

            // creating response
            LinearRegressionResponse response = new LinearRegressionResponse();

            // store model as byte array
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(stream);
            oos.writeObject(model);
            oos.close();
            stream.close();
            response.setModel(stream.toByteArray());

            response.setColumns(resultSet.columns());

            response.setMse(mse);

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
