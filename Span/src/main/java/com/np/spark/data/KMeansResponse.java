package com.np.spark.data;

import java.io.Serializable;

public class KMeansResponse implements Serializable {
    private double wssse;
    private String[] columns;
    private byte[] model;

    public KMeansResponse()
    {

    }

    public double getWssse() {
        return wssse;
    }

    public void setWssse(double wssse) {
        this.wssse = wssse;
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
}
