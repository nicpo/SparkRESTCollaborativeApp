package com.np.metastore.management;

import com.np.metastore.data.User;
import com.np.metastore.kernel.UserKernel;
import com.np.metastore.session.SessionPool;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("/user")
public class UserManagement {
    /**
     * @summary Create a new user account
     * @param sid Session id
     * @param data User account data. Specify the following fields: name, e-mail, password, active, admin
     * @return Created user
     * @throws SQLException
     * @throws NoSuchAlgorithmException
     */
    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public static User create(@HeaderParam("sid") String sid, User data) throws SQLException, NoSuchAlgorithmException {
        SessionPool.getInstance().get(sid).requireAdmin(); // check permissions
        AuditManagement.log(sid, "CREATE_USER", data.toString());

        return UserKernel.create(data);
    }

    /**
     * @summary Update user settings except password
     * @param sid Session id
     * @param id User id
     * @param data Updated user. Specify the following fields: name, e-mail, active, admin
     * @return Updated user
     * @throws SQLException
     * @throws NoSuchAlgorithmException
     */
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public static User update(@HeaderParam("sid") String sid, @PathParam("id") String id, User data) throws SQLException, NoSuchAlgorithmException {
        data.setId(id);
        SessionPool.getInstance().get(sid).requireOwnerOrAdmin(data.getId());
        AuditManagement.log(sid, "UPDATE_USER", data.toString());

        return UserKernel.update(data);
    }

    /**
     * @summary Delete user by id
     * @param sid Session id
     * @param id User id
     * @return Number of deleted items
     * @throws SQLException
     */
    @DELETE
    @Path("/{id}")
    @Produces("plain/text")
    public static String delete(@HeaderParam("sid") String sid, @PathParam("id") String id) throws SQLException
    {
        SessionPool.getInstance().get(sid).requireOwnerOrAdmin(id);
        AuditManagement.log(sid, "DELETE_USER", id);

        return UserKernel.delete(id);
    }

    /**
     * @summary Get user by id
     * @param sid Session id
     * @param id User id
     * @return User
     * @throws SQLException
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public static User get(@HeaderParam("sid") String sid, @PathParam("id") String id) throws SQLException
    {
        SessionPool.getInstance().get(sid).requireOwnerOrAdmin(id);
        return UserKernel.get(id);
    }
}
