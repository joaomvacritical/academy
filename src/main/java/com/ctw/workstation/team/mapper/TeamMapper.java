package com.ctw.workstation.team.mapper;

import com.ctw.workstation.team.dto.TeamDTO;
import com.ctw.workstation.team.dto.TeamRequestDTO;
import com.ctw.workstation.team.entity.Team;

public class TeamMapper {

    public static TeamDTO toDTO(Team team) {
        return new TeamDTO(
                team.getId(),
                team.getName(),
                team.getProduct(),
                team.getDefaultLocation(),
                team.getCreatedAt(),
                team.getModifiedAt()
        );
    }

    public static Team toEntity(TeamRequestDTO dto) {
        Team team = new Team();
        team.setName(dto.getName());
        team.setProduct(dto.getProduct());
        team.setDefaultLocation(dto.getDefaultLocation());
        return team;
    }

    public static void updateEntity(Team team, TeamRequestDTO dto) {
        team.setName(dto.getName());
        team.setProduct(dto.getProduct());
        team.setDefaultLocation(dto.getDefaultLocation());
    }
}
