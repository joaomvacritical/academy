
package com.ctw.workstation.common;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.jboss.logging.Logger;

@Provider
public class ThrowableExceptionMapper implements ExceptionMapper<Throwable> {

    private static final Logger log = Logger.getLogger(ThrowableExceptionMapper.class);

    @Override
    public Response toResponse(Throwable exception) {
        log.error("Erro inesperado: " + exception.getMessage(), exception);

        ErrorResponseDTO error = new ErrorResponseDTO(
                Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(),
                "Ocorreu um erro inesperado no servidor."
        );

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(error)
                .build();
    }
}

