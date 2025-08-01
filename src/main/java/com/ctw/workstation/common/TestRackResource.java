package com.ctw.workstation.common;

import com.ctw.workstation.rack.entity.Rack;
import com.ctw.workstation.rack.repository.RackRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;



import java.util.List;

@Path("/api/test")
@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TestRackResource {

    @Inject
    RackRepository rackRepository;

    @POST
    @Path("/setup-racks")
    @Transactional
    public Response setupRacks(List<Rack> racks) {
        // Only allow in test mode
        String profile = System.getProperty("quarkus.profile");
        if (!"test".equals(profile)) {
            return Response.status(Response.Status.FORBIDDEN).entity("Forbidden").build();
        }

        rackRepository.deleteAll();
        racks.forEach(rackRepository::persist);

        return Response.ok().build();
    }
}
