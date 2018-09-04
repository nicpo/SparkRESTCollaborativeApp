package com.np.metastore.management;

import com.np.metastore.data.Analysis;
import com.np.metastore.data.Dataset;
import com.np.metastore.kernel.AnalysisKernel;
import com.np.metastore.kernel.DatasetKernel;
import com.np.metastore.session.Session;
import com.np.metastore.session.SessionPool;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("/analysis")
public class AnalysisManagement {
    /**
     * @summary Delete analysis result
     * @param sid Session id
     * @param id Analysis id
     * @return Number of deleted items
     * @throws SQLException
     */
    @DELETE
    @Path("/{id}")
    @Produces("plain/text")
    public static String delete(@HeaderParam("sid") String sid, @PathParam("id") String id) throws SQLException
    {
        SessionPool.getInstance().get(sid).canDeleteAnalysis(id);
        return AnalysisKernel.delete(id);
    }

    /**
     * @summary Get analysis result by id
     * @param sid Session id
     * @param id Analysis id
     * @return
     * @throws SQLException
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public static Analysis get(@HeaderParam("sid") String sid, @PathParam("id") String id) throws SQLException
    {
        SessionPool.getInstance().get(sid).canViewAnalysis(id);
        return AnalysisKernel.get(id);
    }


    /**
     * @param sid  Session id
     * @param id   Analysis id
     * @param data Analysis. Set the following fields: name, description
     * @return Updated analysis
     * @throws SQLException
     * @summary Update analysis
     */
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public static Analysis update(@HeaderParam("sid") String sid, @PathParam("id") String id, Analysis data) throws SQLException, FileNotFoundException {
        data.setId(id);
        SessionPool.getInstance().get(sid);
        AuditManagement.log(sid, "UPDATE_ANALYSIS", data.toString());
        return AnalysisKernel.update(data);
    }

}
