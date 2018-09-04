package com.np.metastore.management;

import com.np.metastore.data.ActivityType;
import com.np.metastore.data.Permission;
import com.np.metastore.data.PermissionInfo;
import com.np.metastore.kernel.Core;
import com.np.metastore.kernel.PermissionKernel;
import com.np.metastore.session.Session;
import com.np.metastore.session.SessionPool;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Path("/permission")
public class PermissionManagement {
    /**
     * @summary Create a new permission for dataset or analysis
     * @param sid Session id
     * @param data Permission. The following fields should be specified: type (VIEW, COMMENT, ANALYZE, SHARE), user, dataset, analysis.
     * @return Created permission with all fields filled.
     * @throws SQLException
     */
    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public static Permission[] create(@HeaderParam("sid") String sid, Permission[] data) throws SQLException {
        Session sess = SessionPool.getInstance().get(sid);
        AuditManagement.log(sid, "CREATE_PERMISSION", Arrays.toString(data));

        for (Permission d : data)
            d.setCreator(sess.getUserId());

        if(data.length > 0) {
            if((data[0].getAnalysis() != null) && (data[0].getAnalysis().trim() != ""))
                ActivityManagement.log(sid, "", data[0].getAnalysis(), ActivityType.SHARE_ANALYSIS_SHARE, data[0].getUser());
            else
                ActivityManagement.log(sid, data[0].getDataset(), "", ActivityType.SHARE_DATASET_SHARE, data[0].getUser(), "", true);
        }

        sess.requireOwnerOrAdmin(sess.getUserId()); // check permissions
        return PermissionKernel.create(data);
    }

    @POST
    @Path("/dataset/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public static void overrideForDataset(@HeaderParam("sid") String sid, @PathParam("id") String datasetId, PermissionInfo[] info) throws SQLException {
        Session sess = SessionPool.getInstance().get(sid);
        sess.requireOwnerOrAdmin(sess.getUserId()); // check permissions
        AuditManagement.log(sid, "OVERRIDE_PERMISSIONS_FOR_DATASET", datasetId);

        // add to activity log
        // get all users with CURRENT permissions on the dataset
        List<Permission> currentPermissions = Core.listPermissionForDataset(datasetId);
        List<String> currentPermissionsUserIds = new ArrayList<String>();
        for(Permission permission : currentPermissions)
            if(!currentPermissionsUserIds.contains(permission.getUser()))
                currentPermissionsUserIds.add(permission.getUser());
        // get all users with NEW permissions on the dataset
        List<String> newPermissionsUserIds = new ArrayList<String>();
        for(PermissionInfo permission : info)
            if(!newPermissionsUserIds.contains(permission.getUser()))
                newPermissionsUserIds.add(permission.getUser());

        // get users for whom permissions were ADDED
        // (new permissions that are not in current permissions)
        List<String> addedUserIds = new ArrayList<>(newPermissionsUserIds);
        addedUserIds.removeAll(currentPermissionsUserIds);

        // add new user ids to activity log
        for(String userId : addedUserIds) {
                try {
                    ActivityManagement.log(sid, datasetId, "", ActivityType.SHARE_DATASET_SHARE, userId, "", true);
                }
                catch (SQLException ex)
                {}
            }

        // get users for whom permissions were REMOVED
        // (new permissions that are not in current permissions)
        List<String> removedUserIds = new ArrayList<>(currentPermissionsUserIds);
        removedUserIds.removeAll(newPermissionsUserIds);

        // add removed user ids to activity log
        for(String userId : removedUserIds) {
            try {
                ActivityManagement.log(sid, datasetId, "", ActivityType.SHARE_DATASET_UNSHARE, userId, "", true);
            }
            catch (SQLException ex)
            {}
        }

        PermissionKernel.overrideForDataset(sess.getUserId(), datasetId, info);
    }

    @POST
    @Path("/analysis/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public static void overrideForAnalysis(@HeaderParam("sid") String sid, @PathParam("id") String analysisId, PermissionInfo[] info) throws SQLException {
        Session sess = SessionPool.getInstance().get(sid);
        sess.requireOwnerOrAdmin(sess.getUserId()); // check permissions
        AuditManagement.log(sid, "OVERRIDE_PERMISSIONS_FOR_USER", analysisId);

        // add to activity log
        // get all users with CURRENT permissions on the analysis
        List<Permission> currentPermissions = Core.listPermissionForAnalysis(analysisId);
        List<String> currentPermissionsUserIds = new ArrayList<String>();
        for(Permission permission : currentPermissions)
            if(!currentPermissionsUserIds.contains(permission.getUser()))
                currentPermissionsUserIds.add(permission.getUser());
        // get all users with NEW permissions on the dataset
        List<String> newPermissionsUserIds = new ArrayList<String>();
        for(PermissionInfo permission : info)
            if(!newPermissionsUserIds.contains(permission.getUser()))
                newPermissionsUserIds.add(permission.getUser());

        // get users for whom permissions were ADDED
        // (new permissions that are not in current permissions)
        List<String> addedUserIds = new ArrayList<>(newPermissionsUserIds);
        addedUserIds.removeAll(currentPermissionsUserIds);

        // add new user ids to activity log
        for(String userId : addedUserIds) {
            try {
                ActivityManagement.log(sid, "", analysisId, ActivityType.SHARE_ANALYSIS_SHARE, userId);
            }
            catch (SQLException ex)
            {}
        }

        // get users for whom permissions were REMOVED
        // (new permissions that are not in current permissions)
        List<String> removedUserIds = new ArrayList<>(currentPermissionsUserIds);
        removedUserIds.removeAll(newPermissionsUserIds);

        // add removed user ids to activity log
        for(String userId : removedUserIds) {
            try {
                ActivityManagement.log(sid, "", analysisId, ActivityType.SHARE_ANALYSIS_UNSHARE, userId);
            }
            catch (SQLException ex)
            {}
        }

        PermissionKernel.overrideForAnalysis(sess.getUserId(), analysisId, info);
    }

    /**
     * @summary Delete permission by id
     * @param sid Session id
     * @param id Permission id
     * @return Number of deleted items
     * @throws SQLException
     */
    @DELETE
    @Path("/{id}")
    @Produces("plain/text")
    public static String delete(@HeaderParam("sid") String sid, @PathParam("id") String id) throws SQLException
    {
        SessionPool.getInstance().get(sid);
        AuditManagement.log(sid, "DELETE_PERMISSION", id);

        return PermissionKernel.delete(id);
    }

    /**
     * @summary Delete permission by user and dataset id
     * @param sid Session id
     * @param userId User id
     * @param datasetId Dataset id
     * @return Number of deleted items
     * @throws SQLException
     */
    @DELETE
    @Path("/{uid}/dataset/{did}")
    @Produces("plain/text")
    public static String deleteForDataset(@HeaderParam("sid") String sid, @PathParam("uid") String userId, @PathParam("did") String datasetId) throws SQLException
    {
        SessionPool.getInstance().get(sid);
        AuditManagement.log(sid, "DELETE_PERMISSIONS_FOR_DATASET", datasetId);

        return PermissionKernel.deleteForDataset(userId, datasetId);
    }

    /**
     * @summary Delete permission by user and dataset id
     * @param sid Session id
     * @param userId User id
     * @param analysisId Analysis id
     * @return Number of deleted items
     * @throws SQLException
     */
    @DELETE
    @Path("/{uid}/analysis/{aid}")
    @Produces("plain/text")
    public static String deleteForAnalysis(@HeaderParam("sid") String sid, @PathParam("uid") String userId, @PathParam("aid") String analysisId) throws SQLException
    {
        SessionPool.getInstance().get(sid);
        AuditManagement.log(sid, "DELETE_PERMISSIONS_FOR_ANALYSIS", analysisId);

        return PermissionKernel.deleteForAnalysis(userId, analysisId);
    }

}
