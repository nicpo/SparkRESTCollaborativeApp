package com.np.spark.data;

import com.np.metastore.data.Dataset;

public interface SparkPredictRequest extends SparkRequest {
    public String getUser();
    public void setUser(String user);
    public String getQuery();
    public String getDatasetName();
    public String getDatasetUrl();
    public String getDatasetTable();
}
