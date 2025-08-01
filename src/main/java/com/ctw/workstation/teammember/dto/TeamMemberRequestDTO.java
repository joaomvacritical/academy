package com.ctw.workstation.teammember.dto;

public class TeamMemberRequestDTO {

    private String name;
    private String ctwId;
    private Long teamId;

    public TeamMemberRequestDTO() {}

    public TeamMemberRequestDTO(String name, String ctwId, Long teamId) {
        this.name = name;
        this.ctwId = ctwId;
        this.teamId = teamId;
    }

    public String getName() {
        return name;
    }

    public String getCtwId() {
        return ctwId;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCtwId(String ctwId) {
        this.ctwId = ctwId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }
}
