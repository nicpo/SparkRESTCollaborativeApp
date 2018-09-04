package com.np.spark.kernel;

import com.np.spark.data.AnalysisTypes;
import org.apache.spark.mllib.linalg.Vectors;
import org.apache.spark.mllib.regression.LinearRegressionModel;

public class LinearRegressionPredictKernel extends PredictKernel {
    public LinearRegressionPredictKernel() {
    }

    @Override
    double modelPredict(Object model, double[] features) {
        double prediction = ((LinearRegressionModel)model).predict(Vectors.dense(features));
        return prediction;
    }

    @Override
    String getBaseAnalysisType() {
        return AnalysisTypes.LinearRegression;
    }
}
