package com.np.spark.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name="SVMRequest")
@XmlAccessorType(XmlAccessType.NONE)
public class SVMRequest implements Serializable, SparkRequest {
    @XmlElement
    private String name;

    @XmlElement
    private String query;

    @XmlElement
    private String user;

    @XmlElement
    private double trainingSize;

    @XmlElement
    private int iterations;

    @XmlElement
    private double miniBatchFraction;

    @XmlElement
    private String regularizationType;

    @XmlElement
    private double regularizationParameter;

    @XmlElement
    private double stepSize;


    public SVMRequest() {

    }

    public SVMRequest(String name, String query, String user, double trainingSize, int iterations, double miniBatchFraction, String regularizationType, double regularizationParameter, double stepSize) {
        this.name = name;
        this.query = query;
        this.user = user;
        this.trainingSize = trainingSize;
        this.iterations = iterations;
        this.miniBatchFraction = miniBatchFraction;
        this.regularizationType = regularizationType;
        this.regularizationParameter = regularizationParameter;
        this.stepSize = stepSize;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    @Override
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public double getTrainingSize() {
        return trainingSize;
    }

    public void setTrainingSize(double trainingSize) {
        this.trainingSize = trainingSize;
    }

    public int getIterations() {
        return iterations;
    }

    public void setIterations(int iterations) {
        this.iterations = iterations;
    }

    public double getMiniBatchFraction() {
        return miniBatchFraction;
    }

    public void setMiniBatchFraction(double miniBatchFraction) {
        this.miniBatchFraction = miniBatchFraction;
    }

    public String getRegularizationType() {
        return regularizationType;
    }

    public void setRegularizationType(String regularizationType) {
        this.regularizationType = regularizationType;
    }

    public double getRegularizationParameter() {
        return regularizationParameter;
    }

    public void setRegularizationParameter(double regularizationParameter) {
        this.regularizationParameter = regularizationParameter;
    }

    public double getStepSize() {
        return stepSize;
    }

    public void setStepSize(double stepSize) {
        this.stepSize = stepSize;
    }
}
