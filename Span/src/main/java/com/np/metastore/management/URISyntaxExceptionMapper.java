package com.np.metastore.management;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;

@Provider
public class URISyntaxExceptionMapper implements ExceptionMapper<URISyntaxException> {
    @Override
    public Response toResponse(URISyntaxException exception) {
        return Response.status(404).type(MediaType.TEXT_PLAIN_TYPE).entity(exception.getMessage()).build();
    }
}
