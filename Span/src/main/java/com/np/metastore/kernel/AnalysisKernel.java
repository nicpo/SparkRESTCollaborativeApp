package com.np.metastore.kernel;

import com.np.metastore.data.Analysis;
import com.np.metastore.data.Dataset;
import com.np.metastore.management.SystemException;
import com.np.spark.kernel.Util;
import com.np.util.Config;

import javax.ws.rs.Path;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class AnalysisKernel {

    public static Analysis queue(Analysis data) throws SQLException {
        data.setId(Config.getShortUUID());
        data.setStartTime(System.currentTimeMillis());
        try {
            System.out.println("data="+data.toString());

            PreparedStatement stmt = Config.getInstance().getConn()
                    .prepareStatement("INSERT INTO aa_analysis (analysis_id, analysis_name, analysis_type, analysis_query, analysis_user, analysis_request, analysis_state, analysis_starttime, analysis_description) VALUES(?,?,?,?,?,?,?,?,?)");
            stmt.setString(1, data.getId());
            stmt.setString(2, data.getName());
            stmt.setString(3, data.getType());
            stmt.setString(4, data.getQuery());
            stmt.setString(5, data.getUser());
            stmt.setString(6, data.getRequest());
            stmt.setString(7, data.getState());
            stmt.setLong(8, data.getStartTime());
            stmt.setString(9, data.getDescription());
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

    public static Analysis finish(Analysis data) throws SQLException {
        data.setEndTime(System.currentTimeMillis());
        try {
            System.out.println("data="+data.toString());

            PreparedStatement stmt = Config.getInstance().getConn()
                    .prepareStatement("UPDATE aa_analysis SET analysis_state=?, analysis_output=?, analysis_endtime=? WHERE analysis_id=?");
            stmt.setString(1, data.getState());
            stmt.setString(2, data.getOutput());
            stmt.setLong(3, data.getEndTime());
            stmt.setString(4, data.getId());
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

    public static String delete(String id) throws SQLException
    {
        int ret=0;
        try {
            PreparedStatement stmt = Config.getInstance().getConn()
                    .prepareStatement("DELETE FROM aa_analysis WHERE analysis_id=?");
            stmt.setString(1, id);
            ret = stmt.executeUpdate();
            stmt.close();
            Config.getInstance().getConn().commit();
            return String.valueOf(ret);
        } catch (Exception e) {
            e.printStackTrace();
            Config.getInstance().getConn().rollback();
            throw e;
        }
    }

    public static Analysis update(Analysis data) throws SQLException, FileNotFoundException {
        try {
            System.out.println("data=" + data.toString());

            PreparedStatement stmt = Config.getInstance().getConn()
                    .prepareStatement("UPDATE aa_analysis SET analysis_name=?, analysis_description=? WHERE analysis_id=?");
            stmt.setString(1, data.getName());
            stmt.setString(2, data.getDescription());
            stmt.setString(3, data.getId());

            if (stmt.executeUpdate() == 0)
                throw new ItemNotFound(data.getId());

            stmt.close();
            Config.getInstance().getConn().commit();
            return data;
        } catch (Exception e) {

            e.printStackTrace();
            Config.getInstance().getConn().rollback();
            throw e;
        }
    }

    public static Analysis get(String id) throws SQLException
    {
        Analysis result = null;
        try {
            PreparedStatement stmt = Config.getInstance().getConn()
                    .prepareStatement("SELECT analysis_id, analysis_name, analysis_type, analysis_query, analysis_user, analysis_request, analysis_state, analysis_output, analysis_starttime, analysis_endtime, analysis_description FROM aa_analysis WHERE analysis_id=?");
            stmt.setString(1, id);
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                result = new Analysis(res);
            }
            stmt.close();
            Config.getInstance().getConn().commit();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            Config.getInstance().getConn().rollback();
            throw e;
        }
    }

}
