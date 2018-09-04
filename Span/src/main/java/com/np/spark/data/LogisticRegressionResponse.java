package com.np.spark.data;

import com.np.spark.kernel.LogisticRegressionYMap;
import org.apache.spark.mllib.classification.LogisticRegressionModel;

import java.io.Serializable;
import java.util.HashMap;

public class LogisticRegressionResponse implements Serializable {
    private double fMeasure;
    private double precision;
    private HashMap<Double, Double> fMeasures= new HashMap<>(); // classes and fMeasures
    private HashMap<String, Double> features= new HashMap<>(); // features and weights
    private String[] columns;
    private byte[] model;
    private byte[] map; // part of the model

    public LogisticRegressionResponse() {
    }

    public String[] getColumns() {
        return columns;
    }

    public void setColumns(String[] columns) {
        this.columns = columns;
    }

    public double getfMeasure() {
        return fMeasure;
    }

    public void setfMeasure(double fMeasure) {
        this.fMeasure = fMeasure;
    }

    public double getPrecision() {
        return precision;
    }

    public void setPrecision(double precision) {
        this.precision = precision;
    }

    public HashMap<Double, Double> getfMeasures() {
        return fMeasures;
    }

    public void setfMeasures(HashMap<Double, Double> fMeasures) {
        this.fMeasures = fMeasures;
    }

    public HashMap<String, Double> getFeatures() {
        return features;
    }

    public void setFeatures(HashMap<String, Double> features) {
        this.features = features;
    }

    public byte[] getModel() {
        return model;
    }

    public void setModel(byte[] model) {
        this.model = model;
    }

    public byte[] getMap() {
        return map;
    }

    public void setMap(byte[] map) {
        this.map = map;
    }
}
