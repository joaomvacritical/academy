package com.ctw.workstation.rack_asset.dto;

public class RackAssetUpdateDTO {


    private Long rackId;

    public RackAssetUpdateDTO() {}

    public RackAssetUpdateDTO(String assetTag, Long rackId) {
        this.assetTag = assetTag;
        this.rackId = rackId;
    }

    private String assetTag;

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
