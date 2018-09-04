package com.np.spark.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Map;

@XmlRootElement(name="RandomForestRequest")
@XmlAccessorType(XmlAccessType.NONE)
public class RandomForestRequest implements Serializable, SparkRequest {
    @XmlElement
    private String name;

    @XmlElement
    private String query;

    @XmlElement
    private String user;

    @XmlElement
    private String algo; // "classification" or "regression"

    @XmlElement
    private int numTrees; // number of trees

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
    private double subsamplingRate; // This parameter specifies the sizeByUrl of the dataset used for training each tree in the forest, as a fraction of the sizeByUrl of the original dataset.

    @XmlElement
    private String  featureSubsetStrategy; // "auto"

    @XmlElement
    private double trainingSize; // default: 0.6


    public RandomForestRequest() {

    }

    public RandomForestRequest(String name, String query, String user, String algo, int numTrees, int numClasses, Map<Integer, Integer> categoricalFeaturesInfo, String impurity, int maxDepth, int maxBins, double subsamplingRate, String featureSubsetStrategy, double trainingSize) {
        this.name = name;
        this.query = query;
        this.user = user;
        this.algo = algo;
        this.numTrees = numTrees;
        this.numClasses = numClasses;
        this.categoricalFeaturesInfo = categoricalFeaturesInfo;
        this.impurity = impurity;
        this.maxDepth = maxDepth;
        this.maxBins = maxBins;
        this.subsamplingRate = subsamplingRate;
        this.featureSubsetStrategy = featureSubsetStrategy;
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

    public int getNumTrees() {
        return numTrees;
    }

    public void setNumTrees(int numTrees) {
        this.numTrees = numTrees;
    }

    public double getSubsamplingRate() {
        return subsamplingRate;
    }

    public void setSubsamplingRate(double subsamplingRate) {
        this.subsamplingRate = subsamplingRate;
    }

    public String getFeatureSubsetStrategy() {
        return featureSubsetStrategy;
    }

    public void setFeatureSubsetStrategy(String featureSubsetStrategy) {
        this.featureSubsetStrategy = featureSubsetStrategy;
    }
}

