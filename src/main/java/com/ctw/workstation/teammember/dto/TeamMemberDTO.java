package com.ctw.workstation.teammember.dto;

public class TeamMemberDTO {
    private Long id;
    private String name;
    private String ctwId;
    private Long teamId;
    private String teamName;

    public TeamMemberDTO() {}

    public TeamMemberDTO(Long id, String name, String ctwId, Long teamId, String teamName) {
        this.id = id;
        this.name = name;
        this.ctwId = ctwId;
        this.teamId = teamId;
        this.teamName = teamName;
    }

    public Long getId() {
        return id;
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

    public String getTeamName() {
        return teamName;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
