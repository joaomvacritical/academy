
package com.ctw.workstation.common;

import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;


@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {

    @Override
    public Response toResponse(NotFoundException ex) {
        ErrorResponseDTO error = new ErrorResponseDTO(
                Response.Status.NOT_FOUND.getStatusCode(),
                ex.getMessage()
        );
        return Response.status(Response.Status.NOT_FOUND).entity(error).build();
    }
}

