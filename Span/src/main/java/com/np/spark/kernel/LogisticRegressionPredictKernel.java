package com.np.spark.kernel;

import com.np.metastore.data.Analysis;
import com.np.metastore.kernel.AnalysisKernel;
import com.np.metastore.management.SystemException;
import com.np.spark.data.AnalysisTypes;
import com.np.spark.data.LinearRegressionResponse;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.mllib.classification.LogisticRegressionModel;
import org.apache.spark.mllib.linalg.Vectors;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.types.StructType;
import parquet.org.codehaus.jackson.JsonNode;
import parquet.org.codehaus.jackson.map.ObjectMapper;
import parquet.org.codehaus.jackson.map.ObjectReader;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.SQLException;

public class LogisticRegressionPredictKernel extends PredictKernel {
    public LogisticRegressionPredictKernel() {
    }

    @Override
    double modelPredict(Object model, double[] features) {
        double prediction = ((LogisticRegressionModel)model).predict(Vectors.dense(features));
        return prediction;
    }

    @Override
    String getBaseAnalysisType() {
        return AnalysisTypes.LogisticRegression;
    }

    /**
     * We override this method because we need map/re-map Y for logistic regression
     * @param analysisId
     * @param newSchema
     * @param rowRDD
     * @return
     * @throws SQLException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Override
    protected JavaRDD<Row> predictRow(String analysisId, final StructType newSchema, JavaRDD<Row> rowRDD) throws SQLException, IOException, ClassNotFoundException {
        // load the model
        final Object model = loadModelFromAnalysis (analysisId);
        final LogisticRegressionYMap map = loadLogisticRegressionYMap (analysisId);


        JavaRDD<Row> valuesAndPreds = rowRDD.map(new Function<Row, Row>() {
            @Override
            public Row call(Row row) throws Exception {
                // modelPredict new value
                double[] features = new double[row.size()];
                for (int i=0;i<features.length;i++)
                    features[i] = Util.getAsDoubleExceptionUnhandled(row, i);

                double prediction = modelPredict(model, features);
                int intLabel = (int) Math.round(prediction);
                double originalLabel = map.get(intLabel);

                return getRowWithPrediction(newSchema, originalLabel, features);
            }
        });
        return valuesAndPreds;
    }


    protected LogisticRegressionYMap loadLogisticRegressionYMap(String analysisId) throws IOException, SQLException, ClassNotFoundException {
        Analysis analysisWithModel = AnalysisKernel.get(analysisId);

        if (!analysisWithModel.getType().equals(getBaseAnalysisType()))
            throw new SystemException("Incorrect base analysis type");

        if (!analysisWithModel.getState().equals("DONE"))
            throw new SystemException("Referenced analyze doesn't finished successfully");

        final ObjectReader or = new ObjectMapper().reader(LinearRegressionResponse.class);
        JsonNode tree = or.readTree(analysisWithModel.getOutput());
        byte[] serializedModel = tree.get("map").getBinaryValue();

        ByteArrayInputStream stream = new ByteArrayInputStream(serializedModel);
        ObjectInputStream ois = new ObjectInputStream(stream);
        final LogisticRegressionYMap map = (LogisticRegressionYMap)ois.readObject();
        ois.close();
        stream.close();

        return map;
    }


}
