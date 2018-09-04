package com.np.spark.data;

import scala.Tuple2;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class SummaryResponse implements Serializable {
    private double min;
    private double max;
    private double mean;
    private double variance;
    private double q1;
    private double q3;
    private double median;
    private Integer bins[];
    private String[] columns;

    public SummaryResponse() {
    }

    public String[] getColumns() {
        return columns;
    }

    public void setColumns(String[] columns) {
        this.columns = columns;
    }


    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getMean() {
        return mean;
    }

    public void setMean(double mean) {
        this.mean = mean;
    }

    public double getVariance() {
        return variance;
    }

    public void setVariance(double variance) {
        this.variance = variance;
    }

    public double getQ1() {
        return q1;
    }

    public void setQ1(double q1) {
        this.q1 = q1;
    }

    public double getQ3() {
        return q3;
    }

    public void setQ3(double q3) {
        this.q3 = q3;
    }

    public double getMedian() {
        return median;
    }

    public void setMedian(double median) {
        this.median = median;
    }

    public Integer[] getBins() {
        return bins;
    }

    public void setBins(Integer[] bins) {
        this.bins = bins;
    }
}
