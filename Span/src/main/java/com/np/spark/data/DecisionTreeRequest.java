package com.np.spark.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Map;

@XmlRootElement(name="DecisionTreeRequest")
@XmlAccessorType(XmlAccessType.NONE)
public class DecisionTreeRequest implements Serializable, SparkRequest {
    @XmlElement
    private String name;

    @XmlElement
    private String query;

    @XmlElement
    private String user;

    @XmlElement
    private String algo; // "classification" or "regression"

    @XmlElement
    private int numClasses; //  number of classes for classification (for classification only). Default value is 2 (binary classification)

    @XmlElement
    private Map<Integer, Integer> categoricalFeaturesInfo; // Map storing arity of categorical features. E.g., an entry (n -> k) indicates that feature n is categorical with k categories indexed from 0: {0, 1, ..., k-1}.

    @XmlElement
    private String impurity; // Criterion used for information gain calculation. Supported values (classification): "gini" (recommended) or "entropy".   Supported values (regression): "variance".

    @XmlElement
    private int maxDepth; // Maximum depth of the tree. E.g., depth 0 means 1 leaf node; depth 1 means 1 internal node + 2 leaf nodes. (suggested value: 4)

    @XmlElement
    private int maxBins; // maximum number of bins used for splitting features (suggested value: 100)

    @XmlElement
    private double trainingSize; // default: 0.6


    public DecisionTreeRequest() {

    }

    public DecisionTreeRequest(String name, String query, String user, String algo, int numClasses, Map<Integer, Integer> categoricalFeaturesInfo, String impurity, int maxDepth, int maxBins, double trainingSize) {
        this.name = name;
        this.query = query;
        this.user = user;
        this.algo = algo;
        this.numClasses = numClasses;
        this.categoricalFeaturesInfo = categoricalFeaturesInfo;
        this.impurity = impurity;
        this.maxDepth = maxDepth;
        this.maxBins = maxBins;
        this.trainingSize = trainingSize;
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

    public String getAlgo() {
        return algo;
    }

    public void setAlgo(String algo) {
        this.algo = algo;
    }

    public boolean itsClassification()
    {
        if (algo!=null && algo.equals("classification"))
            return true;
        else
            return false;
    }

    public int getNumClasses() {
        return numClasses;
    }

    public void setNumClasses(int numClasses) {
        this.numClasses = numClasses;
    }

    public Map<Integer, Integer> getCategoricalFeaturesInfo() {
        return categoricalFeaturesInfo;
    }

    public void setCategoricalFeaturesInfo(Map<Integer, Integer> categoricalFeaturesInfo) {
        this.categoricalFeaturesInfo = categoricalFeaturesInfo;
    }

    public String getImpurity() {
        return impurity;
    }

    public void setImpurity(String impurity) {
        this.impurity = impurity;
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    public void setMaxDepth(int maxDepth) {
        this.maxDepth = maxDepth;
    }

    public int getMaxBins() {
        return maxBins;
    }

    public void setMaxBins(int maxBins) {
        this.maxBins = maxBins;
    }

    public double getTrainingSize() {
        return trainingSize;
    }

    public void setTrainingSize(double trainingSize) {
        this.trainingSize = trainingSize;
    }
}
