package com.np.spark.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name="LinearRegressionRequest")
@XmlAccessorType(XmlAccessType.NONE)
public class LinearRegressionRequest implements Serializable, SparkRequest {
    @XmlElement
    private String name;

    @XmlElement
    private String query;

    @XmlElement
    private String user;

    @XmlElement
    private int iterations;

    public LinearRegressionRequest() {

    }

    public LinearRegressionRequest(String name, String query, String user, int iterations) {
        this.name = name;
        this.query = query;
        this.user = user;
        this.iterations = iterations;
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

    public int getIterations() {
        return iterations;
    }

    public void setIterations(int iterations) {
        this.iterations = iterations;
    }
}
