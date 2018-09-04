package com.np.metastore.management;

import com.np.metastore.data.Audit;
import com.np.metastore.kernel.AuditKernel;
import com.np.metastore.session.SessionPool;

import java.sql.SQLException;

public class AuditManagement {
    public static void log(String sid, String type, String info) throws SQLException {
        String userId = SessionPool.getInstance().get(sid).getUserId();
        AuditKernel.create(new Audit(type, userId, info));
    }

    public static void log(String sid, String type) throws SQLException {
        log(sid, type, "");
    }

}
