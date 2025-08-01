package com.ctw.workstation.team.dto;

import java.util.Date;

public class TeamDTO {
    private Long id;
    private String name;
    private String product;
    private String defaultLocation;
    private Date createdAt;
    private Date modifiedAt;


    public TeamDTO(Long id, String name, String product, String defaultLocation, Date createdAt, Date modifiedAt) {
        this.id = id;
        this.name = name;
        this.product = product;
        this.defaultLocation = defaultLocation;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
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

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
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

    public TeamDTO() {
    }


}
