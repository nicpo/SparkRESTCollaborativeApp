package com.np.spark.kernel;

import com.np.spark.data.AnalysisTypes;
import org.apache.spark.mllib.linalg.Vectors;
import org.apache.spark.mllib.tree.model.DecisionTreeModel;
import org.apache.spark.mllib.tree.model.RandomForestModel;

public class RandomForestPredictKernel extends PredictKernel {
    public RandomForestPredictKernel() {
    }

    @Override
    double modelPredict(Object model, double[] features) {
        double prediction = ((RandomForestModel)model).predict(Vectors.dense(features));
        return prediction;
    }

    @Override
    String getBaseAnalysisType() {
        return AnalysisTypes.RandomForest;
    }

}
