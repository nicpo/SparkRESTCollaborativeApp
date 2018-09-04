package com.np.metastore.management;

import com.np.metastore.data.*;
import com.np.metastore.kernel.*;
import com.np.metastore.session.Session;
import com.np.metastore.session.SessionPool;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("/core")
public class CoreManagement {
    /**
     * @summary List all analysis in db, require admin permissions
     * @param sid Session id
     * @return Array of available analysis results
     * @throws java.sql.SQLException
     */
    @GET
    @Path("/list/analysis")
    @Produces(MediaType.APPLICATION_JSON)
    public static ArrayList<Analysis> listAnalysis(@HeaderParam("sid") String sid) throws SQLException
    {
        SessionPool.getInstance().get(sid).requireAdmin();
        return Core.listAnalysis();
    }

    /**
     * @summary List analysis available for logged user
     * @param sid Session id
     * @return Array of available analysis results
     * @throws SQLException
     */
    @GET
    @Path("/list/analysisForUser")
    @Produces(MediaType.APPLICATION_JSON)
    public static ArrayList<Analysis> listAnalysisForUser(@HeaderParam("sid") String sid) throws SQLException
    {
        Session sess = SessionPool.getInstance().get(sid);
        return Core.listAnalysisForUser(sess.getUserId());
    }

    /**
     * @summary List activity the specified dataset
     * @param sid Session id
     * @param id Dataset id
     * @return Array of actiivity for the specified dataset
     * @throws SQLException
     */
    @GET
    @Path("/list/activityForDataset/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public static ArrayList<Activity> listActivityForDataset(@HeaderParam("sid") String sid, @PathParam("id") String id) throws SQLException
    {
        Session sess = SessionPool.getInstance().get(sid);
        return Core.listActivityForDataset(id);
    }

    /**
     * @summary List activity the specified analysis
     * @param sid Session id
     * @param id Analysis id
     * @return Array of activity for the specified dataset
     * @throws SQLException
     */
    @GET
    @Path("/list/activityForAnalysis/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public static ArrayList<Activity> listActivityForAnalysis(@HeaderParam("sid") String sid, @PathParam("id") String id) throws SQLException
    {
        Session sess = SessionPool.getInstance().get(sid);
        return Core.listActivityForAnalysis(id);
    }

    /**
     * @summary List activity for logged user
     * @param sid Session id
     * @return Array of activity for datasets and analyses the user has access to
     * @throws SQLException
     */
    @GET
    @Path("/list/activityForUser")
    @Produces(MediaType.APPLICATION_JSON)
    public static ArrayList<Activity> listActivityForUser(@HeaderParam("sid") String sid) throws SQLException
    {
        Session sess = SessionPool.getInstance().get(sid);
        return Core.listActivityForUser(sess.getUserId());
    }

    /**
     * Only admin can do it.
     * @summary Get available audit records
     * @param sid Session id
     * @return
     * @throws SQLException
     */
    @GET
    @Path("/list/audit")
    @Produces(MediaType.APPLICATION_JSON)
    public static ArrayList<Audit> listAudit(@HeaderParam("sid") String sid) throws SQLException
    {
        Session sess = SessionPool.getInstance().get(sid);
        return Core.listAudit(sess.getUserId());
    }

    /**
     * @summary Get comments for dataset
     * @param sid Session id
     * @param id Dataset id
     * @return List of comments
     * @throws SQLException
     */
    @GET
    @Path("/list/commentForDataset/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public static ArrayList<Comment> listCommentDataset(@HeaderParam("sid") String sid, @PathParam("id") String id) throws SQLException
    {
        SessionPool.getInstance().get(sid);
        return Core.listCommentForDataset(id);
    }

    /**
     * @summary Get comments for analysis
     * @param sid Session id
     * @param id Analysis id
     * @return List of comments
     * @throws SQLException
     */
    @GET
    @Path("/list/commentForAnalysis/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public static ArrayList<Comment> listCommentForAnalysis(@HeaderParam("sid") String sid, @PathParam("id") String id) throws SQLException
    {
        SessionPool.getInstance().get(sid);
        return Core.listCommentForAnalysis(id);
    }

    /**
     * @summary List all datasets in db, require admin permissions
     * @param sid Session id
     * @return List of datasets
     * @throws SQLException
     */
    @GET
    @Path("/list/dataset")
    @Produces(MediaType.APPLICATION_JSON)
    public static ArrayList<Dataset> listDataset(@HeaderParam("sid") String sid) throws SQLException
    {
        SessionPool.getInstance().get(sid).requireAdmin();
        return Core.listDataset();
    }

    /**
     * @summary List datasets available for specified user
     * @param sid Session id
     * @return List of datasets
     * @throws SQLException
     */
    @GET
    @Path("/list/datasetForUser")
    @Produces(MediaType.APPLICATION_JSON)
    public static ArrayList<Dataset> listDatasetForUser(@HeaderParam("sid") String sid) throws SQLException
    {
        Session sess = SessionPool.getInstance().get(sid);
        return Core.listDatasetForUser(sess.getUserId());
    }

    /**
     * @summary List all permissions for specified user
     * @param sid Session id
     * @param id User id
     * @return List of permissions
     * @throws SQLException
     */
    @GET
    @Path("/list/permissionForUser/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public static ArrayList<Permission> listPermissionForUser(@HeaderParam("sid") String sid, @PathParam("id") String id) throws SQLException
    {
        SessionPool.getInstance().get(sid);
        return Core.listPermissionForUser(id);
    }

    /**
     * @summary List all permissions for dataset
     * @param sid Session id
     * @param id Dataset id
     * @return List of permissions
     * @throws SQLException
     */
    @GET
    @Path("/list/permissionForDataset/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public static ArrayList<Permission> listPermissionForDataset(@HeaderParam("sid") String sid, @PathParam("id") String id) throws SQLException
    {
        SessionPool.getInstance().get(sid);
        return Core.listPermissionForDataset(id);
    }

    /**
     * @summary List all permissions for analysis
     * @param sid Session id
     * @param id Analysis id
     * @return List of permissions
     * @throws SQLException
     */
    @GET
    @Path("/list/permissionForAnalysis/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public static ArrayList<Permission> listPermissionForAnalysis(@HeaderParam("sid") String sid, @PathParam("id") String id) throws SQLException
    {
        SessionPool.getInstance().get(sid);
        return Core.listPermissionForAnalysis(id);
    }

    /**
     * List all users in the system (only admin can do it)
     * @param sid Session id
     * @return List of users
     * @throws SQLException
     */
    @GET
    @Path("/list/user")
    @Produces(MediaType.APPLICATION_JSON)
    public static ArrayList<User> listUser(@HeaderParam("sid") String sid) throws SQLException
    {
        SessionPool.getInstance().get(sid).requireAdmin();
        return Core.listUser();
    }

    /**
     * List all users in the system (only admin can do it)
     * @param sid Session id
     * @return List of users
     * @throws SQLException
     */
    @GET
    @Path("/list/user/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public static User listUser(@HeaderParam("sid") String sid, @PathParam("email") String email) throws SQLException
    {
        SessionPool.getInstance().get(sid);
        return Core.getUserByEmail(email);
    }


    /**
     * @summary Login and get user session id
     * @param data Specify email, password, admin
     * @return session id
     * @throws SQLException
     * @throws java.security.NoSuchAlgorithmException
     */
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public static Session login(User data) throws SQLException, NoSuchAlgorithmException
    {
        User dbUser = UserKernel.getByEmail(data.getEmail());

        // Check if user not found
        if (dbUser==null)
            throw new AccessDeniedException("User not found");

        // check that user active
        if (!dbUser.isActive())
            return null;

        // if found user is not admin but admin required
        if (!dbUser.isAdmin() && data.isAdmin())
            throw new AccessDeniedException("User should be admin");

        // check password
        String passwordHash = UserKernel.calcHashForPassword(data.getPassword(), dbUser.getSalt());
        if (!passwordHash.equals(dbUser.getPassword()))
            throw new AccessDeniedException("Incorrect password");

        // now create session and return session key
        Session sess = SessionPool.getInstance().createSession(dbUser);
        AuditManagement.log(sess.getSid(), "LOGIN");
        return sess;
    }

    /**
     * @summary Change user password
     * @param sid Session id
     * @param id User id
     * @param data Updated user. Specify the following fields: password
     * @return Updated user
     * @throws SQLException
     * @throws NoSuchAlgorithmException
     */
    @PUT
    @Path("/changePassword/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public static User updatePassword(@HeaderParam("sid") String sid, @PathParam("id") String id, User data) throws SQLException, NoSuchAlgorithmException {
        data.setId(id);
        SessionPool.getInstance().get(sid).requireOwnerOrAdmin(data.getId());
        AuditManagement.log(sid, "CHANGE PASSWORD");
        return UserKernel.updatePassword(data);
    }

    /**
     * List datasets accessed by specified queries
     * @param sid Session id
     * @param query Query
     * @return List of related datasets
     * @throws SQLException
     */
    @PUT
    @Path("/listDatasetsForQuery")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public static ArrayList<Dataset> listDatasetsForQuery(@HeaderParam("sid") String sid, String query) throws SQLException
    {
        SessionPool.getInstance().get(sid);
        return Core.listDatasetsForQuery(query);
    }

    /**
     * List analysis related to specified dataset
     * @param sid Session id
     * @param datasetId Dataset
     * @return List of related datasets
     * @throws SQLException
     */
    @GET
    @Path("/relatedAnalysisForDataset/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public static ArrayList<Analysis> relatedAnalysisForDataset(@HeaderParam("sid") String sid, @PathParam("id") String datasetId) throws SQLException
    {
        String userId = SessionPool.getInstance().get(sid).getUserId();
        return Core.relatedAnalysisForDataset(userId, datasetId);
    }

    /**
     * Test query and return query schema if query is correct.
     * To get schema of dataset - just query it.
     * @param sid Session id
     * @param query query to test
     * @return
     * @throws URISyntaxException
     * @throws IOException
     * @throws SQLException
     */
    @PUT
    @Path("/testQueryGetSchema")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public static String testQueryGetSchema(@HeaderParam("sid") String sid, String query) throws URISyntaxException, IOException, SQLException {
        SessionPool.getInstance().get(sid);
        return Core.testQueryGetSchema(query);
    }

    /**
     * Delete parquet directory (or any other directory, no additional checks here)
     * by file:// or s3n:// url
     */
    @PUT
    @Path("/deleteParquet")
    @Consumes(MediaType.TEXT_PLAIN)
    public static void deleteParquet(@HeaderParam("sid") String sid, String url) throws URISyntaxException, IOException, SQLException {
        SessionPool.getInstance().get(sid);
        AuditManagement.log(sid, "DELETE PARQUET", url);
        Core.deleteParquet(url);
    }

    /**
     * Calc dataset size by URL
     * by file:// or s3n:// url
     */
    @PUT
    @Path("/datasetSize")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public static DatasetSize datasetSize(@HeaderParam("sid") String sid, String url) throws IOException, URISyntaxException, SQLException {
        SessionPool.getInstance().get(sid);
        AuditManagement.log(sid, "GET DATAST SIZE", url);
        return Core.datasetSize(url);
    }
}
