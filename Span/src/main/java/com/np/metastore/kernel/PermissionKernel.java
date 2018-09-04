package com.np.metastore.kernel;

import com.np.metastore.data.Permission;
import com.np.metastore.data.PermissionInfo;
import com.np.util.Config;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PermissionKernel {

    public static Permission[] create(Permission[] dataArr) throws SQLException {

        try {
            for (Permission data : dataArr) {
                data.setId(Config.getShortUUID());
                System.out.println("data=" + data.toString());

                PreparedStatement stmt = Config.getInstance().getConn()
                        .prepareStatement("INSERT INTO aa_permission (permission_id, permission_type, permission_creator, permission_user, permission_dataset, permission_analysis ) VALUES(?,?,?,?,?,?)");
                stmt.setString(1, data.getId());
                stmt.setString(2, data.getType());
                stmt.setString(3, data.getCreator());
                stmt.setString(4, data.getUser());
                stmt.setString(5, data.getDataset());
                stmt.setString(6, data.getAnalysis());
                stmt.executeUpdate();

                stmt.close();
            }
            Config.getInstance().getConn().commit();
        } catch (Exception e) {
            e.printStackTrace();
            Config.getInstance().getConn().rollback();
            throw e;
        }
        return dataArr;

    }

    public static void overrideForDataset(String creatorId, String datasetId, PermissionInfo[] info) throws SQLException {
        try {
            PreparedStatement stmt = Config.getInstance().getConn()
                    .prepareStatement("DELETE FROM aa_permission WHERE permission_dataset=?");
            stmt.setString(1, datasetId);
            stmt.executeUpdate();
            stmt.close();

            if (info != null)
                for (PermissionInfo pi : info) {
                    if (pi.getPermissions() == null)
                        continue;
                    for (String permission : pi.getPermissions()) {
                        PreparedStatement stmt2 = Config.getInstance().getConn()
                                .prepareStatement("INSERT INTO aa_permission (permission_id, permission_type, permission_creator, permission_user, permission_dataset, permission_analysis ) VALUES(?,?,?,?,?,?)");
                        stmt2.setString(1, Config.getShortUUID());
                        stmt2.setString(2, permission);
                        stmt2.setString(3, creatorId);
                        stmt2.setString(4, pi.getUser());
                        stmt2.setString(5, datasetId);
                        stmt2.setString(6, null);
                        stmt2.executeUpdate();
                        stmt2.close();
                    }
                }

            Config.getInstance().getConn().commit();
            return;
        } catch (Exception e) {
            e.printStackTrace();
            Config.getInstance().getConn().rollback();
            throw e;
        }
    }

    public static void overrideForAnalysis(String creatorId, String analysisId, PermissionInfo[] info) throws SQLException {
        try {
            PreparedStatement stmt = Config.getInstance().getConn()
                    .prepareStatement("DELETE FROM aa_permission WHERE permission_analysis=?");
            stmt.setString(1, analysisId);
            stmt.executeUpdate();
            stmt.close();

            if (info != null)
                for (PermissionInfo pi : info) {
                    if (pi.getPermissions() == null)
                        continue;
                    for (String permission : pi.getPermissions()) {
                        PreparedStatement stmt2 = Config.getInstance().getConn()
                                .prepareStatement("INSERT INTO aa_permission (permission_id, permission_type, permission_creator, permission_user, permission_dataset, permission_analysis ) VALUES(?,?,?,?,?,?)");
                        stmt2.setString(1, Config.getShortUUID());
                        stmt2.setString(2, permission);
                        stmt2.setString(3, creatorId);
                        stmt2.setString(4, pi.getUser());
                        stmt2.setString(5, null);
                        stmt2.setString(6, analysisId);
                        stmt2.executeUpdate();
                        stmt2.close();
                    }
                }

            Config.getInstance().getConn().commit();
            return;
        } catch (Exception e) {
            e.printStackTrace();
            Config.getInstance().getConn().rollback();
            throw e;
        }
    }

    public static String delete(String id) throws SQLException {
        int ret = 0;
        try {
            PreparedStatement stmt = Config.getInstance().getConn()
                    .prepareStatement("DELETE FROM aa_permission WHERE permission_id=?");
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

    public static String deleteForDataset(String userId, String datasetId) throws SQLException {
        int ret = 0;
        try {
            PreparedStatement stmt = Config.getInstance().getConn()
                    .prepareStatement("DELETE FROM aa_permission WHERE permission_user=? AND permission_dataset=?");
            stmt.setString(1, userId);
            stmt.setString(2, datasetId);
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

    public static String deleteForAnalysis(String userId, String analysisId) throws SQLException {
        int ret = 0;
        try {
            PreparedStatement stmt = Config.getInstance().getConn()
                    .prepareStatement("DELETE FROM aa_permission WHERE permission_user=? AND permission_analysis=?");
            stmt.setString(1, userId);
            stmt.setString(2, analysisId);
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

}
