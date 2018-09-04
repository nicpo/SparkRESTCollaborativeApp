package com.np.spark.kernel;

import com.np.spark.data.AnalysisTypes;
import org.apache.spark.mllib.linalg.Vectors;
import org.apache.spark.mllib.tree.model.DecisionTreeModel;

public class DecisionTreePredictKernel extends PredictKernel {
    public DecisionTreePredictKernel() {
    }

    @Override
    double modelPredict(Object model, double[] features) {
        double prediction = ((DecisionTreeModel)model).predict(Vectors.dense(features));
        return prediction;
    }

    @Override
    String getBaseAnalysisType() {
        return AnalysisTypes.DecisionTree;
    }

}
