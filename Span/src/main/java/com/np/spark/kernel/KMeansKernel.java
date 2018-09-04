package com.np.spark.kernel;

import com.np.metastore.data.Analysis;
import com.np.metastore.kernel.AnalysisKernel;
import com.np.spark.data.KMeansRequest;
import com.np.spark.data.KMeansResponse;
import com.np.util.Config;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.mllib.clustering.KMeans;
import org.apache.spark.mllib.clustering.KMeansModel;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.hive.HiveContext;
import parquet.org.codehaus.jackson.map.ObjectMapper;
import parquet.org.codehaus.jackson.map.ObjectWriter;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;

public class KMeansKernel extends SparkKernel {

    public static void process(Analysis an, final KMeansRequest lrr) {
        final HiveContext hiveContext = Config.getInstance().getHiveContext();

        try {
            DataFrame resultSet = hiveContext.sql(lrr.getQuery());

            JavaRDD<Row> rowRDD = resultSet.javaRDD();

            JavaRDD<Vector> parsedData = rowRDD.flatMap(
                    new FlatMapFunction<Row, Vector>() {
                        public Iterable<Vector> call(Row item) {
                            ArrayList<Vector> ret = new ArrayList<Vector>(1);
                            try {
                                double[] points = new double[item.length()];
                                for (int i = 0; i < item.length(); i++)
                                    points[i] = Util.getAsDoubleExceptionUnhandled(item, i);
                                ret.add(Vectors.dense(points));
                            } catch (NumberFormatException ex) {
                                // if any problems with row occurs - we just skip it
                                System.out.println("Skipping row " + item.toString());
                            }
                            return ret;
                        }
                    }
            );
            parsedData.cache();

            KMeansModel model = KMeans.train(parsedData.rdd(), lrr.getClusters(), lrr.getIterations());

            KMeansResponse response = new KMeansResponse();
            response.setColumns(resultSet.columns());

            // store model as byte array
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(stream);
            oos.writeObject(model);
            oos.close();
            stream.close();
            response.setModel(stream.toByteArray());

            double WSSSE = model.computeCost(parsedData.rdd());
            response.setWssse(WSSSE);
            System.out.println("Within Set Sum of Squared Errors = " + WSSSE);

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
