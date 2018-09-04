package com.np.spark.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name="SVMRequest")
@XmlAccessorType(XmlAccessType.NONE)
public class BayesRequest implements Serializable, SparkRequest {
    @XmlElement
    private String name;

    @XmlElement
    private String query;

    @XmlElement
    private String user;

    @XmlElement
    private double trainingSize;

    @XmlElement
    private double lambda;

    public BayesRequest() {

    }

    public BayesRequest(String name, String query, String user, double trainingSize, double lambda) {
        this.name = name;
        this.query = query;
        this.user = user;
        this.trainingSize = trainingSize;
        this.lambda = lambda;
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

    public double getLambda() {
        return lambda;
    }

    public void setLambda(double lambda) {
        this.lambda = lambda;
    }
}
