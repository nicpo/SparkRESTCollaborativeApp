package com.np.spark.kernel;

import com.np.spark.data.AnalysisTypes;
import org.apache.spark.mllib.clustering.KMeansModel;
import org.apache.spark.mllib.linalg.Vectors;

public class KMeansPredictKernel extends PredictKernel {
    public KMeansPredictKernel() {
    }

    @Override
    double modelPredict(Object model, double[] features) {
        double prediction = ((KMeansModel)model).predict(Vectors.dense(features));
        return prediction;
    }

    @Override
    String getBaseAnalysisType() {
        return AnalysisTypes.KMeans;
    }
}
