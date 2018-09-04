package com.np.spark.kernel;

import com.np.metastore.data.Analysis;
import com.np.metastore.kernel.AnalysisKernel;
import com.np.metastore.kernel.Core;
import com.np.spark.data.*;
import org.apache.spark.mllib.regression.LinearRegressionModel;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.catalyst.expressions.GenericRowWithSchema;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import parquet.org.codehaus.jackson.JsonNode;
import parquet.org.codehaus.jackson.map.ObjectMapper;
import parquet.org.codehaus.jackson.map.ObjectReader;
import parquet.org.codehaus.jackson.map.ObjectWriter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SparkKernel {

    public static Analysis queue(final String type, final SparkRequest req) throws IOException, SQLException {
        // Store request
        final ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String request = ow.writeValueAsString(req);

        Analysis an = new Analysis(req.getName(), type, req.getQuery(), req.getUser(), request, "QUEUED", "");
        AnalysisKernel.queue(an);
        return an;
    }

    public static void processQueue() throws IOException, SQLException {
        Analysis an = Core.getQueuedAnalysis();

        if (an==null)
            return;

        ObjectMapper mapper = new ObjectMapper();

        String type = an.getType();
        if (type.equals(AnalysisTypes.Bayes)) {
            BayesRequest req = mapper.reader(BayesRequest.class).readValue(an.getRequest());
            BayesKernel.process(an, req);
        } else if (type.equals(AnalysisTypes.Correlation)) {
            CorrelationRequest req = mapper.reader(CorrelationRequest.class).readValue(an.getRequest());
            CorrelationKernel.process(an, req);
        } else if (type.equals(AnalysisTypes.DecisionTree)) {
            DecisionTreeRequest req = mapper.reader(DecisionTreeRequest.class).readValue(an.getRequest());
            DecisionTreeKernel.process(an, req);
        } else if (type.equals(AnalysisTypes.KMeans)) {
            KMeansRequest req = mapper.reader(KMeansRequest.class).readValue(an.getRequest());
            KMeansKernel.process(an, req);
        } else if (type.equals(AnalysisTypes.LinearRegression)) {
            LinearRegressionRequest req = mapper.reader(LinearRegressionRequest.class).readValue(an.getRequest());
            LinearRegressionKernel.process(an, req);
        } else if (type.equals(AnalysisTypes.LogisticRegression)) {
            LogisticRegressionRequest req = mapper.reader(LogisticRegressionRequest.class).readValue(an.getRequest());
            LogisticRegressionKernel.process(an, req);
        } else if (type.equals(AnalysisTypes.RandomForest)) {
            RandomForestRequest req = mapper.reader(RandomForestRequest.class).readValue(an.getRequest());
            RandomForestKernel.process(an, req);
        } else if (type.equals(AnalysisTypes.Summary)) {
            SummaryRequest req = mapper.reader(SummaryRequest.class).readValue(an.getRequest());
            SummaryKernel.process(an, req);
        } else if (type.equals(AnalysisTypes.SVM)) {
            SVMRequest req = mapper.reader(SVMRequest.class).readValue(an.getRequest());
            SVMKernel.process(an, req);
        } else if (type.equals(AnalysisTypes.BayesPredict)) {
            PredictRequest req = mapper.reader(PredictRequest.class).readValue(an.getRequest());
            new BayesPredictKernel().predict(an, req);
        } else if (type.equals(AnalysisTypes.DecisionTreePredict)) {
            PredictRequest req = mapper.reader(PredictRequest.class).readValue(an.getRequest());
            new DecisionTreePredictKernel().predict(an, req);
        } else if (type.equals(AnalysisTypes.LinearRegressionPredict)) {
            PredictRequest req = mapper.reader(PredictRequest.class).readValue(an.getRequest());
            new LinearRegressionPredictKernel().predict(an, req);
        } else if (type.equals(AnalysisTypes.KMeansPredict)) {
            PredictRequest req = mapper.reader(PredictRequest.class).readValue(an.getRequest());
            new KMeansPredictKernel().predict(an, req);
        } else if (type.equals(AnalysisTypes.RandomForestPredict)) {
            PredictRequest req = mapper.reader(PredictRequest.class).readValue(an.getRequest());
            new RandomForestPredictKernel().predict(an, req);
        } else if (type.equals(AnalysisTypes.SVMPredict)) {
            PredictRequest req = mapper.reader(PredictRequest.class).readValue(an.getRequest());
            new SVMPredictKernel().predict(an, req);
        }  else if (type.equals(AnalysisTypes.LogisticRegressionPredict)) {
            PredictRequest req = mapper.reader(PredictRequest.class).readValue(an.getRequest());
            new LogisticRegressionPredictKernel().predict(an, req);
        }
    }

}
