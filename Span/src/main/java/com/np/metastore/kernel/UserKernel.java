package com.np.metastore.kernel;

import com.np.metastore.data.Dataset;
import com.np.metastore.data.User;
import com.np.metastore.management.SystemException;
import com.np.util.Config;
import org.apache.commons.lang.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class UserKernel {
    public static User create(User data) throws SQLException, NoSuchAlgorithmException {
        data.setId(data.getEmail());
        data.setSalt(UUID.randomUUID().toString());
        validateUser(data);

        try {
            System.out.println("data="+data.toString());

            PreparedStatement stmt = Config.getInstance().getConn()
                    .prepareStatement("INSERT INTO aa_user (user_id, user_name, user_email, user_password, user_salt, user_active, user_admin) VALUES(?,?,?,?,?,?,?)");
            stmt.setString(1, data.getId());
            stmt.setString(2, data.getName());
            stmt.setString(3, data.getEmail());
            stmt.setString(4, calcHashForPassword(data.getPassword(), data.getSalt()));
            stmt.setString(5, data.getSalt());
            stmt.setInt(6, data.isActive() ? 1 : 0);
            stmt.setInt(7, data.isAdmin() ? 1 : 0);
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

    public static void validateUser(User user)
    {
        if (!user.getId().equals(user.getEmail()))
            throw new SystemException("User id must be equals to user email");
        if (!StringUtils.isAlphanumeric(user.getName()))
            throw new SystemException("User name must be alphanumeric");
    }

    public static String calcHashForPassword(String password, String salt) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update((password + salt).getBytes());
        byte byteData[] = md.digest();

        //convert the byte to hex
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }

        // System.out.println("Hex format : " + sb.toString());
        return sb.toString();
    }

    public static User update(User data) throws SQLException, NoSuchAlgorithmException {
        validateUser(data);
        try {
            System.out.println("data="+data.toString());

            PreparedStatement stmt = Config.getInstance().getConn()
                    .prepareStatement("UPDATE aa_user SET user_name=?, user_email=?, user_active=?, user_admin=? WHERE user_id=?");
            stmt.setString(1, data.getName());
            stmt.setString(2, data.getEmail());
            stmt.setInt(3, data.isActive() ? 1 : 0);
            stmt.setInt(4, data.isAdmin() ? 1 : 0);
            stmt.setString(5, data.getId());
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

    public static User updatePassword(User data) throws SQLException, NoSuchAlgorithmException {
        data.setSalt(UUID.randomUUID().toString());
        try {
            System.out.println("data="+data.toString());

            PreparedStatement stmt = Config.getInstance().getConn()
                    .prepareStatement("UPDATE aa_user SET user_password=?, user_salt=? WHERE user_id=?");
            stmt.setString(1, calcHashForPassword(data.getPassword(), data.getSalt()));
            stmt.setString(2, data.getSalt());
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

    public static String delete(String id) throws SQLException
    {
        int ret=0;
        try {
            PreparedStatement stmt = Config.getInstance().getConn()
                    .prepareStatement("DELETE FROM aa_user WHERE user_id=?");
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

    public static User get(String id) throws SQLException
    {
        User result = null;
        try {
            PreparedStatement stmt = Config.getInstance().getConn()
                    .prepareStatement("SELECT user_id, user_name, user_email, user_password, user_salt, user_active, user_admin FROM aa_user WHERE user_id=?");
            stmt.setString(1, id);
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                result = new User(res);
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

    public static User getByEmail(String email) throws SQLException
    {
        User result = null;
        try {
            PreparedStatement stmt = Config.getInstance().getConn()
                    .prepareStatement("SELECT user_id, user_name, user_email, user_password, user_salt, user_active, user_admin FROM aa_user WHERE user_email=?");
            stmt.setString(1, email);
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                result = new User(res);
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
