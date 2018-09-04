package com.np.spark.kernel;

import com.np.spark.data.AnalysisTypes;
import org.apache.spark.mllib.classification.NaiveBayesModel;
import org.apache.spark.mllib.classification.SVMModel;
import org.apache.spark.mllib.linalg.Vectors;

public class BayesPredictKernel extends PredictKernel {
    public BayesPredictKernel() {
    }

    @Override
    double modelPredict(Object model, double[] features) {
        double prediction = ((NaiveBayesModel)model).predict(Vectors.dense(features));
        return prediction;
    }

    @Override
    String getBaseAnalysisType() {
        return AnalysisTypes.Bayes;
    }

}
