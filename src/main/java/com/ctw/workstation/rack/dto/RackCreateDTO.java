package com.ctw.workstation.rack.dto;

public class RackCreateDTO {
    public String serialNumber;
    public String status;
    public String defaultLocation;

    public RackCreateDTO(String serialNumber, String status, String defaultLocation) {}

    public RackCreateDTO() {}

    public String getDefaultLocation() {
        return defaultLocation;
    }

    public void setDefaultLocation(String defaultLocation) {
        this.defaultLocation = defaultLocation;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
