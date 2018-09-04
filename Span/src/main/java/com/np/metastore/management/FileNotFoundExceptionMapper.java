package com.np.metastore.management;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.io.FileNotFoundException;

@Provider
public class FileNotFoundExceptionMapper implements ExceptionMapper<FileNotFoundException> {
    @Override
    public Response toResponse(FileNotFoundException exception) {
        return Response.status(404).type(MediaType.TEXT_PLAIN_TYPE).entity(exception.getMessage()).build();
    }
}
