package com.np.spark.kernel;

import com.np.metastore.data.Analysis;
import com.np.metastore.kernel.AnalysisKernel;
import com.np.spark.data.CorrelationRequest;
import com.np.spark.data.CorrelationResponse;
import com.np.util.Config;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.mllib.linalg.Matrix;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors;
import org.apache.spark.mllib.stat.Statistics;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.hive.HiveContext;
import parquet.org.codehaus.jackson.map.ObjectMapper;
import parquet.org.codehaus.jackson.map.ObjectWriter;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;

public class CorrelationKernel extends SparkKernel implements Serializable {

    public static void process(Analysis an, CorrelationRequest cr) {
        final HiveContext hiveContext = Config.getInstance().getHiveContext();

        try {
            DataFrame resultSet = hiveContext.sql(cr.getQuery());

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

            final String method;
            if (cr.getMethod()!=null && !cr.getMethod().equals(""))
                method = cr.getMethod();
            else
                method = "pearson";
            Matrix correlation = Statistics.corr(parsedData.rdd(), method);

            // fill-in data
            CorrelationResponse response = new CorrelationResponse();
            response.setColumns(resultSet.columns());

            double mat[][] = new double[correlation.numCols()][correlation.numRows()];

            double[] correlationAsRow = correlation.toArray();

            for (int i = 0; i < correlation.numCols(); i++)
                for (int i1 = 0; i1 < correlation.numRows(); i1++)
                    mat[i][i1] = correlationAsRow[i * correlation.numRows() + i1];

            response.setMatrix(mat);

            final ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String outputJSON = ow.writeValueAsString(response);
            an.setOutput(outputJSON);
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
