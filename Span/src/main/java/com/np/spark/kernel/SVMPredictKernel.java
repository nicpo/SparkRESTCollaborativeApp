package com.np.spark.kernel;

import com.np.spark.data.AnalysisTypes;
import org.apache.spark.mllib.classification.SVMModel;
import org.apache.spark.mllib.clustering.KMeansModel;
import org.apache.spark.mllib.linalg.Vectors;

public class SVMPredictKernel extends PredictKernel {
    public SVMPredictKernel() {
    }

    @Override
    double modelPredict(Object model, double[] features) {
        double prediction = ((SVMModel)model).predict(Vectors.dense(features));
        return prediction;
    }

    @Override
    String getBaseAnalysisType() {
        return AnalysisTypes.SVM;
    }

}
