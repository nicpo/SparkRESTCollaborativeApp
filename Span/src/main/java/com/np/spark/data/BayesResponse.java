package com.np.spark.data;

import java.io.Serializable;
import java.util.HashMap;

public class BayesResponse implements Serializable {
    private double accuracy;
    private String[] columns;
    private byte[] model;
    private double[] pi;
    private double[][] theta;


    public BayesResponse() {
    }

    public double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public String[] getColumns() {
        return columns;
    }

    public void setColumns(String[] columns) {
        this.columns = columns;
    }

    public byte[] getModel() {
        return model;
    }

    public void setModel(byte[] model) {
        this.model = model;
    }

    public double[] getPi() {
        return pi;
    }

    public void setPi(double[] pi) {
        this.pi = pi;
    }

    public double[][] getTheta() {
        return theta;
    }

    public void setTheta(double[][] theta) {
        this.theta = theta;
    }
}

