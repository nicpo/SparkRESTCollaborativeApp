package com.np.metastore.data;

/**
 * Created by Nick on 10/5/2015.
 */
public interface ActivityType {
    // Analyses
    public static final String ANALYSIS_BAYES = "ANALYSIS_BAYES";
    public static final String ANALYSIS_SUMMARY = "ANALYSIS_SUMMARY";
    public static final String ANALYSIS_CORRELATE = "ANALYSIS_CORRELATE";
    public static final String ANALYSIS_KMEANS = "ANALYSIS_KMEANS";
    public static final String ANALYSIS_LINEAR = "ANALYSIS_LINEAR";
    public static final String ANALYSIS_LOGISTIC = "ANALYSIS_LOGISTIC";
    public static final String ANALYSIS_SVM = "ANALYSIS_SVM";
    public static final String ANALYSIS_DTREE = "ANALYSIS_DTREE";
    public static final String ANALYSIS_RFOREST = "ANALYSIS_RFOREST";

    // Predictions
    public static final String PREDICT_BAYES = "PREDICT_BAYES";
    public static final String PREDICT_LINEAR = "PREDICT_LINEAR";
    public static final String PREDICT_LOGISTIC = "PREDICT_LOGISTIC";
    public static final String PREDICT_SVM = "PREDICT_SVM";
    public static final String PREDICT_KMEANS = "PREDICT_KMEANS";
    public static final String PREDICT_DTREE = "PREDICT_DTREE";
    public static final String PREDICT_RFOREST = "PREDICT_RFOREST";

    // Commenting
    public static final String COMMENT_DATASET_CREATE = "COMMENT_DATASET_CREATE";
    public static final String COMMENT_DATASET_UPDATE = "COMMENT_DATASET_UPDATE";
    public static final String COMMENT_ANALYSIS_CREATE = "COMMENT_ANALYSIS_CREATE";
    public static final String COMMENT_ANALYSIS_UPDATE = "COMMENT_ANALYSIS_UPDATE";

    // Sharing
    public static final String SHARE_DATASET_SHARE = "SHARE_DATASET_SHARE";
    public static final String SHARE_ANALYSIS_SHARE = "SHARE_ANALYSIS_SHARE";
    public static final String SHARE_DATASET_UNSHARE = "SHARE_DATASET_UNSHARE";
    public static final String SHARE_ANALYSIS_UNSHARE = "SHARE_ANALYSIS_UNSHARE";
}
