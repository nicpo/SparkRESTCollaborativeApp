package com.np.metastore.management;

import com.np.metastore.data.ActivityType;
import com.np.metastore.data.Comment;
import com.np.metastore.kernel.CommentKernel;
import com.np.metastore.session.Session;
import com.np.metastore.session.SessionPool;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("/comment")
public class CommentManagement {
    /**
     * @summary Create a new comment for analysis or dataset
     * @param sid Session id
     * @param data Comment. Specify the following fields: text, dataset OR analysis, target (optional)
     * @return Created comment
     * @throws SQLException
     */
    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public static Comment create(@HeaderParam("sid") String sid, Comment data) throws SQLException {
        Session sess = SessionPool.getInstance().get(sid); // check permissions
        data.setUser(sess.getUserId());
        Comment result = CommentKernel.create(data);
        if((data.getAnalysis() != null) && (data.getAnalysis().trim() != ""))
            ActivityManagement.log(sid, "", result.getAnalysis(), ActivityType.COMMENT_ANALYSIS_CREATE, result.getId());
        else
            ActivityManagement.log(sid, result.getDataset(), "", ActivityType.COMMENT_DATASET_CREATE, result.getId(), "", true);
        return result;
    }

    /**
     * @summary Update comment
     * @param sid Session id
     * @param id Comment id
     * @param data New comment. Specify the following field: text
     * @return Updated comment
     * @throws SQLException
     */
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public static Comment update(@HeaderParam("sid") String sid, @PathParam("id") String id, Comment data) throws SQLException {
        Session sess = SessionPool.getInstance().get(sid);
        // sess.requireOwnerOrAdmin(data.getUser()); // note that user required
        data.setUser(sess.getUserId());
        data.setId(id);
        Comment result = CommentKernel.update(data);
        if((data.getAnalysis() != null) && (data.getAnalysis().trim() != ""))
            ActivityManagement.log(sid, "", result.getAnalysis(), ActivityType.COMMENT_ANALYSIS_UPDATE, result.getId());
        else
            ActivityManagement.log(sid, result.getDataset(), "", ActivityType.COMMENT_DATASET_UPDATE, result.getId(), "", true);
        return result;
    }

    /**
     * @summary Delete comment
     * @param sid Session id
     * @param id Comment id
     * @return Number of deleted items
     * @throws SQLException
     */
    @DELETE
    @Path("/{id}")
    @Produces("plain/text")
    public static String delete(@HeaderParam("sid") String sid, @PathParam("id") String id) throws SQLException
    {
        SessionPool.getInstance().get(sid);
        return CommentKernel.delete(id);
    }
}
