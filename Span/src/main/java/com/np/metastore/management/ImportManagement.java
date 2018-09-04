package com.np.metastore.management;

import com.np.metastore.data.Analysis;
import com.np.metastore.data.CSVFormat;
import com.np.metastore.data.CSVImportRequest;
import com.np.metastore.data.CSVImportResponse;
import com.np.metastore.kernel.AnalysisKernel;
import com.np.metastore.kernel.ImportKernel;
import com.np.metastore.session.SessionPool;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

@Path("/import")
public class ImportManagement {
    /**
     * @summary Guess CSV file format
     * @param sid Session id
     * @param req Import request
     * @return Number of deleted items
     * @throws java.sql.SQLException
     */
    @POST
    @Path("/guessFormat")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public static CSVFormat guessFormat(@HeaderParam("sid") String sid, CSVImportRequest req) throws SQLException, IOException {
        SessionPool.getInstance().get(sid);
        AuditManagement.log(sid, "GUESS_FORMAT", req.toString());

        InputStream is = ImportKernel.loadS3(req.getBucket(), req.getKey());
        return ImportKernel.guessFormat(is);
    }

    /**
     * Get CSV data sample, parsed using the specified format
     * @param sid
     * @param req
     * @return
     * @throws SQLException
     * @throws IOException
     */
    @POST
    @Path("/sample")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public static String sample(@HeaderParam("sid") String sid, CSVImportRequest req) throws SQLException, IOException {
        SessionPool.getInstance().get(sid);
        AuditManagement.log(sid, "CSV_SAMPLE", req.toString());

        InputStream is = ImportKernel.loadS3(req.getBucket(), req.getKey());
        return ImportKernel.sample(req.getFormat(), is);
    }

    @POST
    @Path("/parquet")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public static CSVImportResponse parquet(@HeaderParam("sid") String sid, CSVImportRequest req) throws SQLException, IOException {
        SessionPool.getInstance().get(sid);
        AuditManagement.log(sid, "IMPORT_PARQUET", req.toString());
        InputStream is = ImportKernel.loadS3(req.getBucket(), req.getKey());
        return ImportKernel.createParquet(req.getFormat(), is, req.getPartitionSize(), req.getColumns(), req.getParquetURL());
    }

}
