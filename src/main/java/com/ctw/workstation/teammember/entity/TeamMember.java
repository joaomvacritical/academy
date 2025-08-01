package com.ctw.workstation.teammember.entity;

import com.ctw.workstation.team.entity.Team;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name = "t_team_member")
public class TeamMember {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "team_member_seq_gen")
    @SequenceGenerator(name = "team_member_seq_gen", sequenceName = "team_member_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    @Column(name = "ctw_id", nullable = false, unique = true)
    private String ctwId;

    @Column(nullable = false)
    private String name;

    @CreationTimestamp
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "created_at", updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "modified_at")
    private Date modifiedAt;

    public TeamMember() {}

    // Getters e setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Team getTeam() { return team; }
    public void setTeam(Team team) { this.team = team; }

    public String getCtwId() { return ctwId; }
    public void setCtwId(String ctwId) { this.ctwId = ctwId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Date getCreatedAt() { return createdAt; }

    public Date getModifiedAt() { return modifiedAt; }
}
