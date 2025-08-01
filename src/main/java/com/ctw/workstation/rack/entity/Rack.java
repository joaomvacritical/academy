package com.ctw.workstation.rack.entity;


import com.ctw.workstation.team.entity.Team;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;


@Entity
@Table(name = "t_rack")

public class Rack {

    public enum Status {
        AVAILABLE,
        BOOKED,
        UNAVAILABLE;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rack_seq_gen")
    @SequenceGenerator(name = "rack_seq_gen", sequenceName = "rack_seq", allocationSize = 1)
    private Long id;


    @Column(name = "serial_number", length = 30, nullable = false, unique = true)
    private String serialNumber;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", nullable = true)
    @JsonIgnore
    private Team team;

    @Column(name = "team_id", insertable = false, updatable = false, nullable = true)
    private Long teamId;

    @Column(name = "default_location", length = 30)
    private String defaultLocation;


    @Column(name = "created_at")
    @CreationTimestamp
    private Instant createdAt;


    @Column(name = "modified_at")
    @UpdateTimestamp
     private Instant modifiedAt;



    public Rack() {
    }


    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public String getDefaultLocation() {
        return defaultLocation;
    }

    public void setDefaultLocation(String defaultLocation) {
        this.defaultLocation = defaultLocation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Instant modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Status getStatus(String booked) {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }
}




/*package com.ctw.workstation.rack.entity;
import com.ctw.workstation.team.entity.Team;
import jakarta.persistence.*;

        import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name="t_rack")
public class Rack {

    public enum Status {
        AVAILABLE,
        BOOKED,
        UNAVAILABLE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name="serial_number", length = 30, nullable = false)
    private String serialNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    @Column(name ="default_location", length = 30)
    private String defaultLocation;

    @Column(name="modified_at")
    private Date modifiedAt;









}*/

