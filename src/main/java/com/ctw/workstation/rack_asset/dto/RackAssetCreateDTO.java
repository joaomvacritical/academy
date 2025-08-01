package com.ctw.workstation.rack_asset.dto;

public class RackAssetCreateDTO {

    private String assetTag;
    private Long rackId;

    public RackAssetCreateDTO(String assetTag, Long rackId) {
        this.assetTag = assetTag;
        this.rackId = rackId;
    }

    public Long getRackId() {
        return rackId;
    }

    public void setRackId(Long rackId) {
        this.rackId = rackId;
    }

    public String getAssetTag() {
        return assetTag;
    }

    public void setAssetTag(String assetTag) {
        this.assetTag = assetTag;
    }




}
