package com.np.metastore.kernel;

import com.np.metastore.data.Audit;
import com.np.util.Config;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class AuditKernel {
    public static Audit create(Audit data) throws SQLException {
        data.setId(Config.getShortUUID());
        data.setTimestamp(System.currentTimeMillis());
        try {
            System.out.println("data="+data.toString());

            PreparedStatement stmt = Config.getInstance().getConn()
                    .prepareStatement("INSERT INTO aa_audit (audit_id, audit_type, audit_user, audit_timestamp, audit_info) VALUES(?,?,?,?,?)");
            stmt.setString(1, data.getId());
            stmt.setString(2, data.getType());
            stmt.setString(3, data.getUser());
            stmt.setLong(4, data.getTimestamp());
            stmt.setString(5, data.getInfo());
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
