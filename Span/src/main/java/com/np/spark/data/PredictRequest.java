package com.np.spark.data;

import com.np.metastore.data.Dataset;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name="LinearRegressionPredictRequest")
@XmlAccessorType(XmlAccessType.NONE)
public class PredictRequest implements Serializable, SparkPredictRequest {
    @XmlElement
    private String name;

    @XmlElement
    private String modelAnalysisId;

    @XmlElement
    private String query;

    @XmlElement
    private String user;

    @XmlElement
    private String datasetName;

    @XmlElement
    private String datasetUrl;

    @XmlElement
    private String datasetTable;

    public PredictRequest() {
    }

    public PredictRequest(String name, String modelAnalysisId, String query, String user, String datasetName, String datasetUrl, String datasetTable) {
        this.name = name;
        this.modelAnalysisId = modelAnalysisId;
        this.query = query;
        this.user = user;
        this.datasetName = datasetName;
        this.datasetUrl = datasetUrl;
        this.datasetTable = datasetTable;
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

    @Override
    public String getUser() {
        return user;
    }

    @Override
    public void setUser(String user) {
        this.user = user;

    }

    public String getModelAnalysisId() {
        return modelAnalysisId;
    }

    public void setModelAnalysisId(String modelAnalysisId) {
        this.modelAnalysisId = modelAnalysisId;
    }

    @Override
    public String getDatasetName() {
        return datasetName;
    }

    @Override
    public String getDatasetUrl() {
        return datasetUrl;
    }

    @Override
    public String getDatasetTable() {
        return datasetTable;
    }
}
