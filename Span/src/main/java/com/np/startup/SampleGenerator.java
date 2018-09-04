package com.np.startup;

import com.np.metastore.data.*;
import com.np.metastore.kernel.UserKernel;
import com.np.metastore.management.*;
import com.np.spark.data.*;
import com.np.spark.kernel.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;

public class SampleGenerator {

    public SampleGenerator() {
    }

    public void user1Story() throws SQLException, NoSuchAlgorithmException, IOException, URISyntaxException {
        System.out.println("Create users");
        // Create users
        User user1 = UserKernel.create(new User("user1", "user1@gmail.com", "pwd", false));
        User user2 = UserKernel.create(new User("user2", "user2@gmail.com", "pwd", false));

        // Login
        String sessionId = CoreManagement.login(new User("user1", "user1@gmail.com", "pwd", false)).getSid();

        // create dataset for correlation
        Dataset fb = DatasetManagement.create(sessionId, new Dataset("fb", "file:///etc/span/data/fb.parquet", "fbdata", "", "description", "info"));

        // create small dataset for flight
        Dataset fl2005 = DatasetManagement.create(sessionId, new Dataset("flight2005", "file:///etc/span/data/flight2005.parquet", "flight2005", "", "flight 2005 small data file", "info"));

        // create large dataset for flight
        Dataset datasetForSample = DatasetManagement.create(sessionId, new Dataset("flight20057", "file:///etc/span/data/flight2005_2007.parquet", "flight20057", "", "flight 2005-2007 large data file", "info"));
        // System.out.println("Sample:" + DatasetKernel.getSample(datasetForSample.getId(), 5));

        // create small dataset for twitter msgs
        DatasetManagement.create(sessionId, new Dataset("twitter", "file:///etc/span/data/twitter.parquet", "twitter", "", "twitter data file", "info"));

        // create small dataset for twitter msgs with binary Y
        DatasetManagement.create(sessionId, new Dataset("twitter2", "file:///etc/span/data/twitter2.parquet", "twitter2", "", "twitter data file, binary Y", "info"));

        /*
        // create copy
        Dataset dsCopy = new Dataset("fb2", "file:///etc/span/data/fb2.parquet", "fbdata2", "", "description", "info");
        dsCopy.setUser(user1.getId()); // required because we use "Kernel" method, which doesn't set id automatically
        DatasetBuilder.build(dsCopy);

        // add a comment
        Comment commentX = new Comment("This is comment X", dsCopy.getId(), null, "");
        CommentManagement.create(sessionId, commentX);
        */

        // add 2 comments
        Comment comment1 = new Comment("This is comment 1", fb.getId(), null, "", null);
        CommentManagement.create(sessionId, comment1);

        Comment comment2 = new Comment("This is comment 2", fb.getId(), null, "", null);
        CommentManagement.create(sessionId, comment2);

        // Grant permissions for dataset to user2

        PermissionManagement.create(sessionId, new Permission[]{new Permission(PermissionType.VIEW, user2.getId(), fb.getId(), null)});
    }

    public void deleteEverything() throws SQLException, NoSuchAlgorithmException {
        System.out.println("Delete everything");
        // Login as admin
        User admin = UserKernel.getByEmail("maximkr@gmail.com");
        admin.setPassword("pwd");

        // Login
        String sessionId = CoreManagement.login(admin).getSid();

        // Delete analysis results
        ArrayList<Analysis> analysisList = CoreManagement.listAnalysis(sessionId);
        for (Analysis item: analysisList)
            AnalysisManagement.delete(sessionId, item.getId());

        // Delete datasets
        ArrayList<Dataset> datasetList = CoreManagement.listDataset(sessionId);
        for (Dataset item: datasetList)
            DatasetManagement.delete(sessionId, item.getId());

        // Delete users except admins
        ArrayList<User> userList = CoreManagement.listUser(sessionId);
        for (User item: userList)
            if (!item.isAdmin())
                UserManagement.delete(sessionId, item.getId());
    }

    public void user1Analysis() throws SQLException, NoSuchAlgorithmException, IOException {
        System.out.println("User 1 analysis");

        User user1 = UserKernel.getByEmail("user1@gmail.com");
        user1.setPassword("pwd");

        // Login
        String sessionId = CoreManagement.login(user1).getSid();

        SparkKernel sk = new SparkKernel();

        // decision tree
        DecisionTreeRequest dtReq = new DecisionTreeRequest("Test Decision Tree","SELECT ArrDelay, DayOfWeek, DepTime, DepDelay, TaxiIn, TaxiOut, AirTime FROM flight2005", user1.getId(), "regression", 2, null, "variance", 5, 100, 0.6);
        Analysis dtr = sk.queue(AnalysisTypes.DecisionTree, dtReq);

        // decision tree predict
        long suffixDT = System.currentTimeMillis();
        PredictRequest decisionTreePredictReq = new PredictRequest("Test Decision Tree predict", dtr.getId(), "SELECT DayOfWeek, DepTime, DepDelay, TaxiIn, TaxiOut, AirTime FROM flight2005", user1.getId(), "PredictDS"+suffixDT,"file:///etc/span/data/predict"+suffixDT,"predict"+suffixDT);
        sk.queue(AnalysisTypes.DecisionTreePredict, decisionTreePredictReq);

        // random forest
        RandomForestRequest rfReq = new RandomForestRequest("Test Random Forest","SELECT ArrDelay, DayOfWeek, DepTime, DepDelay, TaxiIn, TaxiOut, AirTime FROM flight2005", user1.getId(), "regression", 3, 2, null, "variance", 3, 5, 1.0,"auto",0.6);
        Analysis rfr = sk.queue(AnalysisTypes.RandomForest, rfReq);

        // random forest predict
        long suffixRF = System.currentTimeMillis();
        PredictRequest randomForestPredictReq = new PredictRequest("Test Random Forest predict", rfr.getId(), "SELECT DayOfWeek, DepTime, DepDelay, TaxiIn, TaxiOut, AirTime FROM flight2005", user1.getId(), "PredictDS"+suffixRF, "file:///etc/span/data/predict"+suffixRF,"predict"+suffixRF);
        sk.queue(AnalysisTypes.RandomForestPredict, randomForestPredictReq);


        // correlation
        CorrelationRequest cr = new CorrelationRequest("Test correlation","SELECT likesCount, characterCount FROM fbdata", user1.getId(), "pearson");
        Analysis an = sk.queue(AnalysisTypes.Correlation, cr);

        // logistic regression flight
        LogisticRegressionRequest lrr = new LogisticRegressionRequest("Test logistic regression","SELECT Delayed, DayOfWeek, DepTime, DepDelay, TaxiIn, TaxiOut, AirTime FROM flight2005", user1.getId(), 0.6,"",0);
        Analysis l = sk.queue(AnalysisTypes.LogisticRegression, lrr);

        // logistic regression predict
        long suffixLP = System.currentTimeMillis();
        PredictRequest logisticRegressionPredict = new PredictRequest("Test logistic predict", l.getId(), "SELECT DayOfWeek, DepTime, DepDelay, TaxiIn, TaxiOut, AirTime FROM flight2005", user1.getId(), "PredictDS"+suffixLP,"file:///etc/span/data/predict"+suffixLP,"predict"+suffixLP);
        sk.queue(AnalysisTypes.LogisticRegressionPredict, logisticRegressionPredict);

        // logistic regression twitter
        LogisticRegressionRequest lrrTw = new LogisticRegressionRequest("Test logistic regression","SELECT grp, f1, f2, f3, f4, f5, f6, f7, f8, f9, f10 FROM twitter", user1.getId(), 0.6,"",0);
        sk.queue(AnalysisTypes.LogisticRegression, lrrTw);

        // linear regression flight
        LinearRegressionRequest linearReq = new LinearRegressionRequest("Test linear regression","SELECT ArrDelay, DayOfWeek, DepTime, DepDelay, TaxiIn, TaxiOut, AirTime FROM flight2005", user1.getId(), 100);
        Analysis a = sk.queue(AnalysisTypes.LinearRegression, linearReq);

        // linear regression predict
        long suffixLRP = System.currentTimeMillis();
        PredictRequest linearPredictReq = new PredictRequest("Test linear predict", a.getId(), "SELECT DayOfWeek, DepTime, DepDelay, TaxiIn, TaxiOut, AirTime FROM flight2005", user1.getId(), "PredictDS"+suffixLRP,"file:///etc/span/data/predict-"+suffixLRP,"predict"+suffixLRP);
        sk.queue(AnalysisTypes.LinearRegressionPredict, linearPredictReq);


        // k-means flight
        KMeansRequest kmeansReq = new KMeansRequest("Test K-means clustering","SELECT DayOfWeek, DepTime, DepDelay, TaxiIn, TaxiOut, AirTime FROM flight2005", user1.getId(), 100, 2, 1);
        Analysis b = sk.queue(AnalysisTypes.KMeans, kmeansReq);

        // K-means predict
        long suffixKP = System.currentTimeMillis();
        PredictRequest kmeansPredictReq = new PredictRequest("Test k-means predict", b.getId(), "SELECT DayOfWeek, DepTime, DepDelay, TaxiIn, TaxiOut, AirTime FROM flight2005", user1.getId(), "PredictDS"+suffixKP,"file:///etc/span/data/predict"+suffixKP,"predict"+suffixKP);
        sk.queue(AnalysisTypes.KMeansPredict, kmeansPredictReq);

        // bayes
        BayesRequest bayesReq = new BayesRequest("Test Bayes","SELECT grp, f1, f2, f3, f4, f5, f6, f7, f8, f9, f10 FROM twitter2", user1.getId(), 0.6, 1);
        Analysis br = sk.queue(AnalysisTypes.Bayes, bayesReq);

        // bayes predict
        long suffixB = System.currentTimeMillis();
        PredictRequest bayesPredictReq = new PredictRequest("Test Bayes predict", br.getId(), "SELECT f1, f2, f3, f4, f5, f6, f7, f8, f9, f10 FROM twitter2", user1.getId(), "PredictDS"+suffixB,"file:///etc/span/data/predict"+suffixB,"predict"+suffixB);
        sk.queue(AnalysisTypes.BayesPredict, bayesPredictReq);

        // svm flight
        SVMRequest svmReq = new SVMRequest("Test SVM","SELECT grp, f1, f2, f3, f4, f5, f6, f7, f8, f9, f10 FROM twitter2", user1.getId(), 0.6, 100, 0, "L2", 1, 1);
        Analysis c = sk.queue(AnalysisTypes.SVM, svmReq);

        // SVM predict
        long suffixSVM = System.currentTimeMillis();
        PredictRequest svmPredictReq = new PredictRequest("Test SVM predict", c.getId(), "SELECT f1, f2, f3, f4, f5, f6, f7, f8, f9, f10 FROM twitter2", user1.getId(), "PredictDS"+suffixSVM,"file:///etc/span/data/predict"+suffixSVM,"predict"+suffixSVM);
        sk.queue(AnalysisTypes.SVMPredict, svmPredictReq);


        // summary flight
        SummaryRequest summaryReq = new SummaryRequest("Test summary","SELECT ArrDelay FROM flight20057", user1.getId());
        sk.queue(AnalysisTypes.Summary, summaryReq);

        // Grant permissions for analysis to user2
        User user2 = UserKernel.getByEmail("user2@gmail.com");
        PermissionManagement.create(sessionId, new Permission[]{new Permission(PermissionType.VIEW, user2.getId(), null, an.getId())});
    }

    public void showWhatsVisibleForUser2() throws SQLException, NoSuchAlgorithmException {
        System.out.println("Print visible analysis and datasets");
        User user2 = UserKernel.getByEmail("user2@gmail.com");
        user2.setPassword("pwd");

        // Login
        String sessionId = CoreManagement.login(user2).getSid();

        // Show available analysis
        ArrayList<Analysis> analysisList = CoreManagement.listAnalysisForUser(sessionId);
        for (Analysis item: analysisList)
            System.out.println(item.toString());

        // Show availableDatasets
        ArrayList<Dataset> datasetList = CoreManagement.listDatasetForUser(sessionId);
        for (Dataset item: datasetList)
            System.out.println(item.toString());


    }

}
