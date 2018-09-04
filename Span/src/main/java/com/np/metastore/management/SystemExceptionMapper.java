package com.np.metastore.management;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class SystemExceptionMapper implements ExceptionMapper<SystemException> {
    @Override
    public Response toResponse(SystemException exception) {
        return Response.status(422).type(MediaType.TEXT_PLAIN_TYPE).entity(exception.getMessage()).build();
    }
}
