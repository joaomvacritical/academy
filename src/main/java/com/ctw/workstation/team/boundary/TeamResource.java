package com.ctw.workstation.team.boundary;

import com.ctw.workstation.team.control.TeamService;
import com.ctw.workstation.team.dto.TeamDTO;
import com.ctw.workstation.team.dto.TeamRequestDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;

import java.util.List;

@Path("/teams")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TeamResource {

    @Inject
    Logger log;

    @Inject
    TeamService teamService;

    @GET
    public List<TeamDTO> getAll() {
        log.info("GET /teams - pedido recebido para listar equipas");
        return teamService.getAll();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        log.infof("GET /teams/%d - pedido recebido", id);
        TeamDTO dto = teamService.getById(id);
        return Response.ok(dto).build();
    }

    @POST
    public Response create(TeamRequestDTO dto) {
        log.infof("POST /teams - pedido recebido para criar equipa: %s", dto.getName());
        TeamDTO created = teamService.create(dto);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, TeamRequestDTO dto) {
        log.infof("PUT /teams/%d - pedido recebido para atualizar equipa", id);
        TeamDTO updated = teamService.update(id, dto);
        return Response.ok(updated).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        log.infof("DELETE /teams/%d - pedido recebido para apagar equipa", id);
        teamService.delete(id);
        return Response.noContent().build();
    }
}
