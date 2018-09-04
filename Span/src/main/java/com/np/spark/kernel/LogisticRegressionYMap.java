package com.np.spark.kernel;

import org.apache.spark.api.java.JavaDoubleRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.DoubleFunction;
import org.apache.spark.sql.Row;

import javax.annotation.concurrent.Immutable;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Labels used in Logistic Regression should be {0, 1, ..., k - 1}
 * for k classes multi-label classification — so check the labels
 * in the Y column, convert them to {0, 1, …} before sending to Spark
 * if necessary, and convert back before presenting to the user
*/

@Immutable
public class LogisticRegressionYMap implements Serializable {
    private final List<Double> values;
    private final HashMap<Double, Integer> map;

    public LogisticRegressionYMap(JavaRDD<Row> rowRDD)
    {
        JavaDoubleRDD uniqueY = rowRDD.mapToDouble(new DoubleFunction<Row>() {
            @Override
            public double call(Row row) throws Exception {
                return Util.getAsDouble(row, 0);
            }
        }).distinct();

        // get List of possible values, ordered
        values = uniqueY.collect();

        // Map values to hashmap
        map = new HashMap<>(values.size());
        for (int i=0;i<values.size();i++)
            map.put(values.get(i), i);
    }

    public double get(int index)
    {
        return values.get(index);
    }

    public int getIndex(double value)
    {
        return map.get(value);
    }

    public int getNumClasses()
    {
        return values.size();
    }

    public List<Double> getValues() {
        return values;
    }

    public HashMap<Double, Integer> getMap() {
        return map;
    }
}
