package com.np.metastore.kernel;

import com.np.metastore.data.Comment;
import com.np.util.Config;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class CommentKernel {
    public static Comment create(Comment data) throws SQLException {
        data.setId(Config.getShortUUID());
        data.setCreated(System.currentTimeMillis());
        try {
            System.out.println("data="+data.toString());

            PreparedStatement stmt = Config.getInstance().getConn()
                    .prepareStatement("INSERT INTO aa_comment (comment_id, comment_user, comment_created, comment_text, comment_dataset, comment_analysis, comment_target, comment_reference) VALUES(?,?,?,?,?,?,?,?)");
            stmt.setString(1, data.getId());
            stmt.setString(2, data.getUser());
            stmt.setLong(3, data.getCreated());
            stmt.setString(4, data.getText());
            stmt.setString(5, data.getDataset());
            stmt.setString(6, data.getAnalysis());
            stmt.setString(7, data.getTarget());
            stmt.setString(8, data.getReference());
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

    public static Comment update(Comment data) throws SQLException {
        data.setUpdated(System.currentTimeMillis());
        try {
            System.out.println("data="+data.toString());

            PreparedStatement stmt = Config.getInstance().getConn()
                    .prepareStatement("UPDATE aa_comment SET comment_updated=?, comment_text=? WHERE comment_id=?");
            stmt.setLong(1, data.getUpdated());
            stmt.setString(2, data.getText());
            stmt.setString(3, data.getId());
            if (stmt.executeUpdate() == 0)
                throw new ItemNotFound(data.getId());
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
                    .prepareStatement("DELETE FROM aa_comment WHERE comment_id=?");
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


}
