package com.np.spark.data;

import java.io.Serializable;
import java.util.HashMap;

public class LinearRegressionResponse implements Serializable {
    private double mse;
    private HashMap<String, Double> weights= new HashMap<>(); // features and weights
    private String[] columns;
    private byte[] model;

    public LinearRegressionResponse() {
    }

    public String[] getColumns() {
        return columns;
    }

    public void setColumns(String[] columns) {
        this.columns = columns;
    }

    public double getMse() {
        return mse;
    }

    public void setMse(double mse) {
        this.mse = mse;
    }

    public HashMap<String, Double> getWeights() {
        return weights;
    }

    public void setWeights(HashMap<String, Double> weights) {
        this.weights = weights;
    }

    public byte[] getModel() {
        return model;
    }

    public void setModel(byte[] model) {
        this.model = model;
    }

}

