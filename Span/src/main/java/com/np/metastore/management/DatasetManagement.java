package com.np.metastore.management;

import com.np.metastore.data.Dataset;
import com.np.metastore.kernel.DatasetKernel;
import com.np.metastore.session.Session;
import com.np.metastore.session.SessionPool;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import java.io.*;
import java.net.URISyntaxException;
import java.sql.SQLException;

@Path("/dataset")
public class DatasetManagement {
    /**
     * @param sid  Session id
     * @param data Dataset. Set the following fields: name, url, table, query (optional), description, info
     * @return Created dataset
     * @throws SQLException
     * @summary Create a new dataset
     */
    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public static Dataset create(@HeaderParam("sid") String sid, Dataset data) throws SQLException, IOException, URISyntaxException {
        Session sess = SessionPool.getInstance().get(sid); // check permissions
        data.setUser(sess.getUserId());
        AuditManagement.log(sid, "CREATE_DATASET", data.toString());
        return DatasetKernel.create(data);
    }

    /**
     * @param sid  Session id
     * @param id   Dataset id
     * @param data Dataset. Set the following fields: name, url, table, query (optional), description, info
     * @return Updated dataset
     * @throws SQLException
     * @summary Update dataset
     */
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public static Dataset update(@HeaderParam("sid") String sid, @PathParam("id") String id, Dataset data) throws SQLException, IOException, URISyntaxException {
        data.setId(id);
        SessionPool.getInstance().get(sid).canUpdateDataset(data.getId());
        AuditManagement.log(sid, "UPDATE_DATASET", data.toString());
        return DatasetKernel.update(data);
    }

    /**
     * @param sid Session id
     * @param id  Dataset id
     * @return Number of deleted datasets
     * @throws SQLException
     * @summary Delete dataset
     */
    @DELETE
    @Path("/{id}")
    @Produces("plain/text")
    public static String delete(@HeaderParam("sid") String sid, @PathParam("id") String id) throws SQLException {
        SessionPool.getInstance().get(sid).canDeleteDataset(id);
        AuditManagement.log(sid, "DELETE_DATASET", id);
        return DatasetKernel.delete(id);
    }

    /**
     * @param sid Session id
     * @param id  Dataset id
     * @return Dataset
     * @throws SQLException
     * @summary Get dataset by id
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public static Dataset get(@HeaderParam("sid") String sid, @PathParam("id") String id) throws SQLException {
        SessionPool.getInstance().get(sid).canViewDataset(id);
        return DatasetKernel.get(id);
    }

    /**
     * Export dataset to CSV
     * @param sid Session
     * @param id Dataset id
     * @return
     * @throws SQLException
     * @throws IOException
     */
    @GET
    @Path("/csv/{sid}/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public static Response exportToCSV(@PathParam("sid") String sid, @PathParam("id") final String id) throws SQLException, IOException {
        SessionPool.getInstance().get(sid).canViewDataset(id);
        AuditManagement.log(sid, "EXPORT_CSV", id);
        final Dataset ds = DatasetKernel.get(id);

        if (ds==null)
            throw new SystemException("Dataset " + id + " not found");

        StreamingOutput stream = new StreamingOutput() {
            @Override
            public void write(OutputStream os) throws IOException, WebApplicationException {
                try {
                    Writer writer = new BufferedWriter(new OutputStreamWriter(os));
                    DatasetKernel.exportToCSV(writer, ds);
                    writer.close();
                } catch (SQLException e) {
                    throw new IOException(e.getMessage());
                }
            }
        };
        return Response.ok(stream).header("content-disposition", "attachment; filename=\"" + ds.getTable() + ".csv\"").build();
    }

    /**
     * Get dataset sample
     *
     * @param sid Session id
     * @param id  Dataset id
     * @return Data rows
     * @throws SQLException
     */
    @GET
    @Path("/sample/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public static String getSample(@HeaderParam("sid") String sid, @PathParam("id") String id) throws SQLException, IOException {
        SessionPool.getInstance().get(sid).canViewDataset(id);
        AuditManagement.log(sid, "GET_SAMPLE", id);
        return DatasetKernel.getSample(id);
    }

    /**
     * Create dataset snapshot. Required dataset params: name, query, table (unique), url. It creates parquet file,
     * then create dataset, then register it. If something goes wrong, it doesn't delete parquet file
     *
     * @param sid     Session id
     * @param dataset New snapshot dataset params
     * @return Created dataset
     * @throws SQLException
     */
    @POST
    @Path("/snapshot")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public static Dataset snapshot(@HeaderParam("sid") String sid, Dataset dataset) throws SQLException, IOException, URISyntaxException {
        dataset.setUser(SessionPool.getInstance().get(sid).getUserId());
        AuditManagement.log(sid, "SNAPSHOT", dataset.toString());
        return DatasetKernel.snapshot(dataset);
    }
}
