package com.np.metastore.management;

import com.np.metastore.data.Activity;
import com.np.metastore.kernel.ActivityKernel;
import com.np.metastore.kernel.Core;
import com.np.metastore.session.SessionPool;
import com.np.metastore.data.*;
import java.util.ArrayList;

import java.sql.SQLException;

public class ActivityManagement {
    public static void log(String sid, String query, String analysisId, String type, String target, String info) throws SQLException {
        String userId = SessionPool.getInstance().get(sid).getUserId();

        // if query was passed, resolve IDs of datasets included in the query
        if(query != "") {
            ArrayList<Dataset> datasets = Core.listDatasetsForQuery(query);

            // foreach datasetId in datasetIds
            for(Dataset dataset : datasets) {
                ActivityKernel.create(new Activity(type, userId, dataset.getId(), "", target, info));
            }
        }

        // if analysis ID was passed
        if(analysisId != "")
            ActivityKernel.create(new Activity(type, userId, "", analysisId, target, info));
    }

    public static void log(String sid, String query, String analysisId, String type, String target) throws SQLException {
        log(sid, query, analysisId, type, target, "");
    }

    public static void log(String sid, String datasetId, String analysisId, String type, String target, String info, boolean datasetBool) throws SQLException {
        String userId = SessionPool.getInstance().get(sid).getUserId();

        ActivityKernel.create(new Activity(type, userId, datasetId, analysisId, target, info));
    }

    public static void log(String sid, String type, String info) throws SQLException {
        /*String userId = SessionPool.getInstance().get(sid).getUserId();
        ActivityKernel.create(new Activity(type, userId, info));*/
        log(sid, "", "", type, "", info);
    }

    public static void log(String sid, String type) throws SQLException {
        log(sid, type, "");
    }

}
