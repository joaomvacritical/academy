package com.ctw.workstation.teammember.mapper;

import com.ctw.workstation.teammember.dto.TeamMemberDTO;
import com.ctw.workstation.teammember.dto.TeamMemberRequestDTO;
import com.ctw.workstation.teammember.entity.TeamMember;
import com.ctw.workstation.team.entity.Team;

public class TeamMemberMapper {

    public static TeamMemberDTO toDTO(TeamMember entity) {
        if (entity == null) return null;

        return new TeamMemberDTO(
                entity.getId(),
                entity.getName(),
                entity.getCtwId(),
                entity.getTeam() != null ? entity.getTeam().getId() : null,
                entity.getTeam() != null ? entity.getTeam().getName() : null
        );
    }

    public static TeamMember toEntity(TeamMemberRequestDTO dto, Team team) {
        if (dto == null || team == null) return null;

        TeamMember entity = new TeamMember();
        entity.setName(dto.getName());
        entity.setCtwId(dto.getCtwId());
        entity.setTeam(team);
        return entity;
    }

    public static void updateEntity(TeamMember entity, TeamMemberRequestDTO dto, Team team) {
        if (dto == null || team == null || entity == null) return;

        entity.setName(dto.getName());
        entity.setCtwId(dto.getCtwId());
        entity.setTeam(team);
    }
}
