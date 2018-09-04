package com.np.spark.management;

import com.np.metastore.data.ActivityType;
import com.np.metastore.data.Analysis;
import com.np.metastore.management.ActivityManagement;
import com.np.metastore.management.AuditManagement;
import com.np.metastore.session.Session;
import com.np.metastore.session.SessionPool;
import com.np.spark.data.*;
import com.np.spark.kernel.CorrelationKernel;
import com.np.spark.kernel.SparkKernel;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.sql.SQLException;

/**
 *
 */
@Path("/statistics")
public class StatisticsManagement {
    /**
     * Run Naive Bayes. Y should be the first column in SQL query, features in other columns.
     * @param sid Session id
     * @param bayesRequest Bayes params
     * @return
     * @throws java.io.IOException
     * @throws java.sql.SQLException
     */
    @POST
    @Path("/bayes")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public static Analysis bayes(@HeaderParam("sid") String sid, BayesRequest bayesRequest) throws IOException, SQLException {
        Session sess = SessionPool.getInstance().get(sid);
        AuditManagement.log(sid, AnalysisTypes.Bayes, bayesRequest.toString());
        bayesRequest.setUser(sess.getUserId());
        Analysis result = SparkKernel.queue(AnalysisTypes.Bayes, bayesRequest);
        ActivityManagement.log(sid, bayesRequest.getQuery(), "", ActivityType.ANALYSIS_BAYES, result.getId());
        return result;
    }

    /**
     * Run Naive Bayes predict on existing model.
     * @param sid Session id
     * @param predictRequest
     * @return
     * @throws java.io.IOException
     * @throws java.sql.SQLException
     */
    @POST
    @Path("/bayes/predict")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public static Analysis bayesPredict(@HeaderParam("sid") String sid, PredictRequest predictRequest) throws IOException, SQLException {
        Session sess = SessionPool.getInstance().get(sid);
        AuditManagement.log(sid, AnalysisTypes.BayesPredict, predictRequest.toString());
        predictRequest.setUser(sess.getUserId());
        Analysis result = SparkKernel.queue(AnalysisTypes.BayesPredict, predictRequest);
        ActivityManagement.log(sid, "", result.getId(), ActivityType.PREDICT_BAYES, predictRequest.getQuery());
        return result;
    }

    /**
     * Get correlation matrix for first param in the query
     * @param sid Session id
     * @param correlationRequest Correlation request params
     * @return Correlation matrix
     * @throws IOException
     * @throws SQLException
     */
    @POST
    @Path("/correlation")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public static Analysis correlation(@HeaderParam("sid") String sid, CorrelationRequest correlationRequest) throws IOException, SQLException {
        Session sess = SessionPool.getInstance().get(sid);
        AuditManagement.log(sid, AnalysisTypes.Correlation, correlationRequest.toString());
        correlationRequest.setUser(sess.getUserId());
        Analysis result = SparkKernel.queue(AnalysisTypes.Correlation, correlationRequest);
        ActivityManagement.log(sid, correlationRequest.getQuery(), "", ActivityType.ANALYSIS_CORRELATE, result.getId());
        return result;
    }

    /**
     * Run Decision Tree. Y should be the first column in SQL query, features in other columns.
     * @param sid Session id
     * @param decisionTreeRequest Bayes params
     * @return
     * @throws java.io.IOException
     * @throws java.sql.SQLException
     */
    @POST
    @Path("/decisionTree")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public static Analysis decisionTree(@HeaderParam("sid") String sid, DecisionTreeRequest decisionTreeRequest) throws IOException, SQLException {
        Session sess = SessionPool.getInstance().get(sid);
        AuditManagement.log(sid, AnalysisTypes.DecisionTree, decisionTreeRequest.toString());
        decisionTreeRequest.setUser(sess.getUserId());
        Analysis result = SparkKernel.queue(AnalysisTypes.DecisionTree, decisionTreeRequest);
        ActivityManagement.log(sid, decisionTreeRequest.getQuery(), "", ActivityType.ANALYSIS_DTREE, result.getId());
        return result;
    }

    /**
     * Run Decision Tree predict on existing model.
     * @param sid Session id
     * @param predictRequest
     * @return
     * @throws java.io.IOException
     * @throws java.sql.SQLException
     */
    @POST
    @Path("/decisionTree/predict")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public static Analysis decisionTreePredict(@HeaderParam("sid") String sid, PredictRequest predictRequest) throws IOException, SQLException {
        Session sess = SessionPool.getInstance().get(sid);
        AuditManagement.log(sid, AnalysisTypes.DecisionTreePredict, predictRequest.toString());
        predictRequest.setUser(sess.getUserId());
        Analysis result = SparkKernel.queue(AnalysisTypes.DecisionTreePredict, predictRequest);
        ActivityManagement.log(sid, "", result.getId(), ActivityType.PREDICT_DTREE, predictRequest.getQuery());
        return result;
    }

    /**
     * Run K-means.
     * @param sid Session id
     * @param kmeansRequest k-means request
     * @return
     * @throws java.io.IOException
     * @throws java.sql.SQLException
     */
    @POST
    @Path("/kmeans")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public static Analysis kmeans(@HeaderParam("sid") String sid, KMeansRequest kmeansRequest) throws IOException, SQLException {
        Session sess = SessionPool.getInstance().get(sid);
        AuditManagement.log(sid, AnalysisTypes.KMeans, kmeansRequest.toString());
        kmeansRequest.setUser(sess.getUserId());
        Analysis result = SparkKernel.queue(AnalysisTypes.KMeans, kmeansRequest);
        ActivityManagement.log(sid, kmeansRequest.getQuery(), "", ActivityType.ANALYSIS_KMEANS, result.getId());
        return result;
    }

    /**
     * Run K-Means predict on existing model.
     * @param sid Session id
     * @param predictRequest
     * @return
     * @throws java.io.IOException
     * @throws java.sql.SQLException
     */
    @POST
    @Path("/kmeans/predict")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public static Analysis kmeansPredict(@HeaderParam("sid") String sid, PredictRequest predictRequest) throws IOException, SQLException {
        Session sess = SessionPool.getInstance().get(sid);
        AuditManagement.log(sid, AnalysisTypes.KMeansPredict, predictRequest.toString());
        predictRequest.setUser(sess.getUserId());
        Analysis result = SparkKernel.queue(AnalysisTypes.KMeansPredict, predictRequest);
        ActivityManagement.log(sid, "", result.getId(), ActivityType.PREDICT_KMEANS, predictRequest.getQuery());
        return result;
    }

    /**
     * Run linear regression. Y should be the first column in SQL query, features in other columns.
     * @param sid Session id
     * @param linearRegressionRequest Linear regression params
     * @return
     * @throws java.io.IOException
     * @throws java.sql.SQLException
     */
    @POST
    @Path("/linearRegression")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public static Analysis linearRegression(@HeaderParam("sid") String sid, LinearRegressionRequest linearRegressionRequest) throws IOException, SQLException {
        Session sess = SessionPool.getInstance().get(sid);
        AuditManagement.log(sid, AnalysisTypes.LinearRegression, linearRegressionRequest.toString());
        linearRegressionRequest.setUser(sess.getUserId());
        Analysis result = SparkKernel.queue(AnalysisTypes.LinearRegression, linearRegressionRequest);
        ActivityManagement.log(sid, linearRegressionRequest.getQuery(), "", ActivityType.ANALYSIS_LINEAR, result.getId());
        return result;
    }

    /**
     * Run linear regression based on existing model.
     * @param sid Session id
     * @param predictRequest
     * @return
     * @throws java.io.IOException
     * @throws java.sql.SQLException
     */
    @POST
    @Path("/linearRegression/predict")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public static Analysis linearRegressionPredict(@HeaderParam("sid") String sid, PredictRequest predictRequest) throws IOException, SQLException {
        Session sess = SessionPool.getInstance().get(sid);
        AuditManagement.log(sid, AnalysisTypes.LinearRegressionPredict, predictRequest.toString());
        predictRequest.setUser(sess.getUserId());
        Analysis result = SparkKernel.queue(AnalysisTypes.LinearRegressionPredict, predictRequest);
        ActivityManagement.log(sid, "", result.getId(), ActivityType.PREDICT_LINEAR, predictRequest.getQuery());
        return result;
    }

    /**
     * Run logistic regression. Y should be the first column in SQL query, features in other columns.
     * @param sid Session id
     * @param logisticRegressionRequest Logistic regression params
     * @return
     * @throws IOException
     * @throws SQLException
     */
    @POST
    @Path("/logisticRegression")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public static Analysis logisticRegression(@HeaderParam("sid") String sid, LogisticRegressionRequest logisticRegressionRequest) throws IOException, SQLException {
        Session sess = SessionPool.getInstance().get(sid);
        AuditManagement.log(sid, AnalysisTypes.LogisticRegression, logisticRegressionRequest.toString());
        logisticRegressionRequest.setUser(sess.getUserId());
        Analysis result = SparkKernel.queue(AnalysisTypes.LogisticRegression, logisticRegressionRequest);
        ActivityManagement.log(sid, logisticRegressionRequest.getQuery(), "", ActivityType.ANALYSIS_LOGISTIC, result.getId());
        return result;
    }

    /**
     * Run linear regression based on existing model.
     * @param sid Session id
     * @param predictRequest
     * @return
     * @throws java.io.IOException
     * @throws java.sql.SQLException
     */
    @POST
    @Path("/logisticRegression/predict")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public static Analysis logisticRegressionPredict(@HeaderParam("sid") String sid, PredictRequest predictRequest) throws IOException, SQLException {
        Session sess = SessionPool.getInstance().get(sid);
        AuditManagement.log(sid, AnalysisTypes.LogisticRegressionPredict, predictRequest.toString());
        predictRequest.setUser(sess.getUserId());
        Analysis result = SparkKernel.queue(AnalysisTypes.LogisticRegressionPredict, predictRequest);
        ActivityManagement.log(sid, "", result.getId(), ActivityType.PREDICT_LOGISTIC, predictRequest.getQuery());
        return result;
    }

    /**
     * Run Random Forest. Y should be the first column in SQL query, features in other columns.
     * @param sid Session id
     * @param randomForestRequest SVM params
     * @return
     * @throws java.io.IOException
     * @throws java.sql.SQLException
     */
    @POST
    @Path("/randomForest")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public static Analysis randomForest(@HeaderParam("sid") String sid, RandomForestRequest randomForestRequest) throws IOException, SQLException {
        Session sess = SessionPool.getInstance().get(sid);
        AuditManagement.log(sid, AnalysisTypes.RandomForest, randomForestRequest.toString());
        randomForestRequest.setUser(sess.getUserId());
        Analysis result = SparkKernel.queue(AnalysisTypes.RandomForest, randomForestRequest);
        ActivityManagement.log(sid, randomForestRequest.getQuery(), "", ActivityType.ANALYSIS_RFOREST, result.getId());
        return result;
    }

    /**
     * Run Random Forest predict on existing model.
     * @param sid Session id
     * @param predictRequest
     * @return
     * @throws java.io.IOException
     * @throws java.sql.SQLException
     */
    @POST
    @Path("/randomForest/predict")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public static Analysis randomForestPredict(@HeaderParam("sid") String sid, PredictRequest predictRequest) throws IOException, SQLException {
        Session sess = SessionPool.getInstance().get(sid);
        AuditManagement.log(sid, AnalysisTypes.RandomForestPredict, predictRequest.toString());
        predictRequest.setUser(sess.getUserId());
        Analysis result = SparkKernel.queue(AnalysisTypes.RandomForestPredict, predictRequest);
        ActivityManagement.log(sid, "", result.getId(), ActivityType.PREDICT_RFOREST, predictRequest.getQuery());
        return result;
    }

    /**
     * Get summary statistics
     * @param sid Session id
     * @param summaryRequest Params
     * @return
     * @throws IOException
     * @throws SQLException
     */
    @POST
    @Path("/summary")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public static Analysis summary(@HeaderParam("sid") String sid, SummaryRequest summaryRequest) throws IOException, SQLException {
        Session sess = SessionPool.getInstance().get(sid);
        AuditManagement.log(sid, AnalysisTypes.Summary, summaryRequest.toString());
        summaryRequest.setUser(sess.getUserId());
        Analysis result = SparkKernel.queue(AnalysisTypes.Summary, summaryRequest);
        ActivityManagement.log(sid, summaryRequest.getQuery(), "", ActivityType.ANALYSIS_SUMMARY, result.getId());
        return result;
    }

    /**
     * Run SVM. Y should be the first column in SQL query, features in other columns.
     * @param sid Session id
     * @param svmRequest SVM params
     * @return
     * @throws java.io.IOException
     * @throws java.sql.SQLException
     */
    @POST
    @Path("/svm")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public static Analysis svm(@HeaderParam("sid") String sid, SVMRequest svmRequest) throws IOException, SQLException {
        Session sess = SessionPool.getInstance().get(sid);
        AuditManagement.log(sid, AnalysisTypes.SVM, svmRequest.toString());
        svmRequest.setUser(sess.getUserId());
        Analysis result = SparkKernel.queue(AnalysisTypes.SVM, svmRequest);
        ActivityManagement.log(sid, svmRequest.getQuery(), "", ActivityType.ANALYSIS_SVM, result.getId());
        return result;
    }

    /**
     * Run SVM predict on existing model.
     * @param sid Session id
     * @param predictRequest
     * @return
     * @throws java.io.IOException
     * @throws java.sql.SQLException
     */
    @POST
    @Path("/svm/predict")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public static Analysis svmPredict(@HeaderParam("sid") String sid, PredictRequest predictRequest) throws IOException, SQLException {
        Session sess = SessionPool.getInstance().get(sid);
        AuditManagement.log(sid, AnalysisTypes.SVMPredict, predictRequest.toString());
        predictRequest.setUser(sess.getUserId());
        Analysis result = SparkKernel.queue(AnalysisTypes.SVMPredict, predictRequest);
        ActivityManagement.log(sid, "", result.getId(), ActivityType.PREDICT_SVM, predictRequest.getQuery());
        return result;
    }

}