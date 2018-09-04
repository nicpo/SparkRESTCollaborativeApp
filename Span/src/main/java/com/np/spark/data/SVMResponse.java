package com.np.spark.data;

import java.io.Serializable;
import java.util.HashMap;

public class SVMResponse implements Serializable {
    private double auroc;
    private double intercept;
    private HashMap<String, Double> weights= new HashMap<>(); // features and weights
    private String[] columns;
    private byte[] model;


    public SVMResponse() {
    }

    public String[] getColumns() {
        return columns;
    }

    public void setColumns(String[] columns) {
        this.columns = columns;
    }

    public HashMap<String, Double> getWeights() {
        return weights;
    }

    public void setWeights(HashMap<String, Double> weights) {
        this.weights = weights;
    }

    public double getAuroc() {
        return auroc;
    }

    public void setAuroc(double auroc) {
        this.auroc = auroc;
    }

    public double getIntercept() {
        return intercept;
    }

    public void setIntercept(double intercept) {
        this.intercept = intercept;
    }


    public byte[] getModel() {
        return model;
    }

    public void setModel(byte[] model) {
        this.model = model;
    }
}

