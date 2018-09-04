package com.np.spark.data;

import java.io.Serializable;

public class RandomForestResponse implements Serializable {
    private String[] columns;
    private byte[] model;
    private double error;
    private String debugTree;

    public RandomForestResponse() {
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

    public double getError() {
        return error;
    }

    public void setError(double error) {
        this.error = error;
    }

    public String getDebugTree() {
        return debugTree;
    }

    public void setDebugTree(String debugTree) {
        this.debugTree = debugTree;
    }
}

