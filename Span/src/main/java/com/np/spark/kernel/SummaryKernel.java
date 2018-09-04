package com.np.spark.kernel;

import com.np.metastore.data.Analysis;
import com.np.metastore.kernel.AnalysisKernel;
import com.np.spark.data.SummaryRequest;
import com.np.spark.data.SummaryResponse;
import com.np.util.Config;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.*;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors;
import org.apache.spark.mllib.stat.MultivariateStatisticalSummary;
import org.apache.spark.mllib.stat.Statistics;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.hive.HiveContext;
import parquet.org.codehaus.jackson.map.ObjectMapper;
import parquet.org.codehaus.jackson.map.ObjectWriter;
import scala.Tuple2;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;

public class SummaryKernel extends SparkKernel implements Serializable {
    public static void process(Analysis an, final SummaryRequest cr) {
        final HiveContext hiveContext = Config.getInstance().getHiveContext();

        try {
            DataFrame resultSet = hiveContext.sql(cr.getQuery());

            JavaRDD<Row> rowRDD = resultSet.javaRDD();

            JavaRDD<Vector> parsedData = rowRDD.flatMap(
                    new FlatMapFunction<Row, Vector>() {
                        public Iterable<Vector> call(Row item) {
                            ArrayList<Vector> ret = new ArrayList<Vector>(1);
                            try {
                                double[] points = new double[1];
                                points[0] = Util.getAsDoubleExceptionUnhandled(item, 0);
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

            MultivariateStatisticalSummary summary = Statistics.colStats(parsedData.rdd());

            SummaryResponse response = new SummaryResponse();
            response.setColumns(resultSet.columns());
            response.setMin(summary.min().toArray()[0]);
            response.setMax(summary.max().toArray()[0]);
            response.setMean(summary.mean().toArray()[0]);
            response.setVariance(summary.variance().toArray()[0]);

            // calc histogram

            // convert vectors of values to pairs <bin, 1>
            final double min = response.getMin();
            final double max = response.getMax();
            final double binWidth = (max-min)/10;

            JavaPairRDD<Integer, Integer> bins = parsedData.mapToPair(new PairFunction<Vector, Integer, Integer>() {
                @Override
                public Tuple2<Integer, Integer> call(Vector s) {
                    double value = s.toArray()[0];
                    int bin = (int)Math.floor((value - min) / binWidth);
                    if (bin==10) // max value goes to last (9) bin.
                        bin=9;
                    return new Tuple2<Integer, Integer>(bin, 1);
                }
            });

            // count values in each bin
            JavaPairRDD<Integer, Integer> counts = bins.reduceByKey(new Function2<Integer, Integer, Integer>() {
                @Override
                public Integer call(Integer i1, Integer i2) {
                    return i1 + i2;
                }
            }).sortByKey();

            // convert pairs <bin,count> to linear array of counts
            Integer binsArr[] = new Integer[10];
            for (int i=0;i<10;i++)
                binsArr[i]=0;
            for (Tuple2<Integer, Integer> item : counts.collect())
                binsArr[item._1()]=item._2();

            response.setBins(binsArr);

            // now calc median

            // convert RDD of vectors to rdd of pairs X,X
            JavaPairRDD<Double, Double> pairRDD = parsedData.mapToPair(new PairFunction<Vector, Double, Double>() {
                @Override
                public Tuple2<Double, Double> call(Vector vector) throws Exception {
                    double value = vector.toArray()[0];
                    return new Tuple2<Double, Double>(value, value);
                }
            });

            // Sort it
            JavaPairRDD<Double, Double> sortedRDD = pairRDD.sortByKey();

            // now attach index to each value
            JavaPairRDD<Tuple2<Double, Double>, Long> zippedWithIndex = sortedRDD.zipWithIndex();

            // now revert index and value
            JavaPairRDD<Long, Double> indexedValues =  zippedWithIndex.mapToPair(new PairFunction<Tuple2<Tuple2<Double, Double>, Long>, Long, Double>() {
                @Override
                public Tuple2<Long, Double> call(Tuple2<Tuple2<Double, Double>, Long> v1) throws Exception {
                    return new Tuple2<Long, Double>(v1._2(), v1._1()._1());
                }
            });

            long countM = indexedValues.count();

            if (countM > 0) {
                Tuple2<Long, Long> medianPos = getMedianPosition(0, countM - 1);
                double median = getValue(medianPos, indexedValues);
                response.setMedian(median);

                Tuple2<Long, Long> q1Pos = getMedianPosition(0, medianPos._1());
                double q1 = getValue(q1Pos, indexedValues);
                response.setQ1(q1);

                Tuple2<Long, Long> q3Pos = getMedianPosition(medianPos._2(), countM - 1);
                double q3 = getValue(q3Pos, indexedValues);
                response.setQ3(q3);
            }

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

    private static double getValue(Tuple2<Long, Long> pos, JavaPairRDD<Long, Double> indexedValues )
    {
        return (indexedValues.lookup(pos._1()).get(0) + indexedValues.lookup(pos._2()).get(0)) / 2;
    }

    private static Tuple2<Long, Long> getMedianPosition(long zeroPos, long endPos) {
        long leftM;
        long rightM;

        if ((endPos - zeroPos) % 2 == 0) {
            leftM = (endPos - zeroPos) / 2 + zeroPos;
            rightM = leftM;
        } else {
            leftM = (endPos - zeroPos - 1) / 2 + zeroPos;
            rightM = leftM + 1;
        }

        return new Tuple2<>(leftM, rightM);
    }
}
