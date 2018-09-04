package com.np.spark.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name="LogisticRegressionRequest")
@XmlAccessorType(XmlAccessType.NONE)
public class LogisticRegressionRequest implements Serializable, SparkRequest {
    @XmlElement
    private String name;

    @XmlElement
    private String query;

    @XmlElement
    private String user;

    @XmlElement
    private double trainingSize;

    @XmlElement
    private String regularizationType;

    @XmlElement
    private double regularizationParameter;

    public LogisticRegressionRequest() {

    }

    public LogisticRegressionRequest(String name, String query, String user, double trainingSize, String regularizationType, double regularizationParameter) {
        this.name = name;
        this.query = query;
        this.user = user;
        this.trainingSize = trainingSize;
        this.regularizationType = regularizationType;
        this.regularizationParameter = regularizationParameter;
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
}
