package com.np.metastore.management;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.nio.file.NoSuchFileException;

@Provider
public class NoSuchFileExceptionMapper implements ExceptionMapper<NoSuchFileException> {
    @Override
    public Response toResponse(NoSuchFileException exception) {
        return Response.status(404).type(MediaType.TEXT_PLAIN_TYPE).entity(exception.getMessage()).build();
    }
}
