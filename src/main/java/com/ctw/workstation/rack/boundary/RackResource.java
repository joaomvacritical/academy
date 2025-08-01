package com.ctw.workstation.rack.boundary;

import com.ctw.workstation.rack.control.RackService;
import com.ctw.workstation.rack.dto.RackCreateDTO;
import com.ctw.workstation.rack.dto.RackResponseDTO;

import com.ctw.workstation.rack.entity.Rack;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/workstation/racks")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RackResource {

    @Inject
    RackService rackService;

    @POST
    public Response createRack(RackCreateDTO dto) {
        RackResponseDTO response = rackService.create(dto);
        return Response.status(Response.Status.CREATED).entity(response).build();
    }

    @GET
    public Response listRacks() {
        List<RackResponseDTO> responseList = rackService.listAll();
        return Response.ok(responseList).build();
    }

    @GET
    @Path("/{id}")
    public Response getRack(@PathParam("id") Long id) {
        RackResponseDTO response = rackService.findById(id);
        return Response.ok(response).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateRack(@PathParam("id") Long id, RackCreateDTO dto) {
        RackResponseDTO response = rackService.update(id, dto);
        return Response.ok(response).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteRack(@PathParam("id") Long id) {
        rackService.delete(id);
        return Response.noContent().build();
    }

    @PUT
    @Path("/{id}/book")
    public Response markAsBooked(@PathParam("id") Long id) {
        RackResponseDTO response = rackService.updateStatus(id, Rack.Status.BOOKED);
        return Response.ok(response).build();
    }



}
