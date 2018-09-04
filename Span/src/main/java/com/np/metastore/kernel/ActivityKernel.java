package com.np.metastore.kernel;

import com.np.metastore.data.Activity;
import com.np.util.Config;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class ActivityKernel {
    public static Activity create(Activity data) throws SQLException {
        data.setId(Config.getShortUUID());
        data.setTimestamp(System.currentTimeMillis());
        try {
            System.out.println("data="+data.toString());

            PreparedStatement stmt = Config.getInstance().getConn()
                    .prepareStatement("INSERT INTO aa_activity (activity_id, activity_type, activity_user, activity_dataset_id, activity_analysis_id, activity_target, activity_timestamp, activity_info) VALUES(?,?,?,?,?,?,?,?)");
            stmt.setString(1, data.getId());
            stmt.setString(2, data.getType());
            stmt.setString(3, data.getUser());
            stmt.setString(4, data.getDataset_id());
            stmt.setString(5, data.getAnalysis_id());
            stmt.setString(6, data.getTarget());
            stmt.setLong(7, data.getTimestamp());
            stmt.setString(8, data.getInfo());
            stmt.executeUpdate();
            stmt.close();
            Config.getInstance().getConn().commit();
        } catch (Exception e) {
            e.printStackTrace();
            Config.getInstance().getConn().rollback();
            throw e;
        }
        return data;

    }


}