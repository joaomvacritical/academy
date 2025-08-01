package com.ctw.workstation.team.dto;

public class TeamRequestDTO {
    private String name;
    private String product;
    private String defaultLocation;

    public TeamRequestDTO() {
    }


    public TeamRequestDTO(String name, String product, String defaultLocation) {
        this.name = name;
        this.product = product;
        this.defaultLocation = defaultLocation;
    }

    public String getDefaultLocation() {
        return defaultLocation;
    }

    public void setDefaultLocation(String defaultLocation) {
        this.defaultLocation = defaultLocation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }


}
