package com.ctw.workstation.teammember.boundary;

import com.ctw.workstation.team.entity.Team;
import com.ctw.workstation.team.repository.TeamRepository;
import com.ctw.workstation.teammember.dto.TeamMemberDTO;
import com.ctw.workstation.teammember.dto.TeamMemberRequestDTO;
import com.ctw.workstation.teammember.entity.TeamMember;
import com.ctw.workstation.teammember.mapper.TeamMemberMapper;
import com.ctw.workstation.teammember.repository.TeamMemberRepository;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.stream.Collectors;

@Path("/team-members")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TeamMemberResource {

    @Inject
    TeamMemberRepository teamMemberRepository;

    @Inject
    TeamRepository teamRepository;

    @GET
    public List<TeamMemberDTO> getAll() {
        return teamMemberRepository.findAll().stream()
                .map(TeamMemberMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        TeamMember member = teamMemberRepository.findById(id);
        if (member == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(TeamMemberMapper.toDTO(member)).build();
    }

    @POST
    @Transactional
    public Response create(TeamMemberRequestDTO dto) {
        Team team = teamRepository.findById(dto.getTeamId());
        if (team == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Equipa com ID " + dto.getTeamId() + " não encontrada.")
                    .build();
        }

        TeamMember member = TeamMemberMapper.toEntity(dto, team);
        teamMemberRepository.persist(member);

        return Response.status(Response.Status.CREATED).entity(TeamMemberMapper.toDTO(member)).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response update(@PathParam("id") Long id, TeamMemberRequestDTO dto) {
        TeamMember member = teamMemberRepository.findById(id);
        if (member == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        Team team = teamRepository.findById(dto.getTeamId());
        if (team == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Equipa com ID " + dto.getTeamId() + " não encontrada.")
                    .build();
        }

        TeamMemberMapper.updateEntity(member, dto, team);
        return Response.ok(TeamMemberMapper.toDTO(member)).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        TeamMember member = teamMemberRepository.findById(id);
        if (member == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        teamMemberRepository.delete(member);
        return Response.noContent().build();
    }
}
