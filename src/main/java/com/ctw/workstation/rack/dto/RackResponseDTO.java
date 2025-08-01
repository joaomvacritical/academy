package com.ctw.workstation.rack.dto;

import com.ctw.workstation.rack.entity.Rack;

import java.time.Instant;

public class RackResponseDTO {

    public Long id;
    public String serialNumber;
    public String status;
    public String defaultLocation;
    public Instant createdAt;
    public Instant modifiedAt;
    public Long teamId;

    public RackResponseDTO() {}

    public RackResponseDTO(Rack rack) {
        this.id = rack.getId();
        this.serialNumber = rack.getSerialNumber();
        this.status = rack.getStatus("BOOKED").name();
        this.defaultLocation = rack.getDefaultLocation();
        this.createdAt = rack.getCreatedAt();
        this.modifiedAt = rack.getModifiedAt();
        this.teamId = rack.getTeamId();
    }
}
