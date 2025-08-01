package com.ctw.workstation.rack_asset.dto;

import com.ctw.workstation.rack_asset.entity.RackAsset;

public class RackAssetResponseDTO {
    private Long id;
    private String assetTag;
    private Long rackId;

    public RackAssetResponseDTO() {
    }

    public RackAssetResponseDTO(RackAsset asset) {
        this.id = asset.getId();
        this.assetTag = asset.getAssetTag();
        this.rackId = asset.getRack() != null ? asset.getRack().getId() : null;
    }

    public Long getId() {
        return id;
    }

    public String getAssetTag() {
        return assetTag;
    }

    public Long getRackId() {
        return rackId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAssetTag(String assetTag) {
        this.assetTag = assetTag;
    }

    public void setRackId(Long rackId) {
        this.rackId = rackId;
    }
}
