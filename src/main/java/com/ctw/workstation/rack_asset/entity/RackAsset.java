package com.ctw.workstation.rack_asset.entity;

import com.ctw.workstation.rack.entity.Rack;
import jakarta.persistence.*;

@Entity
@Table(name = "t_rack_asset")
public class RackAsset {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rack_asset_seq_gen")
    @SequenceGenerator(name = "rack_asset_seq_gen", sequenceName = "rack_asset_seq", allocationSize = 1)
    private Long id;

    @Column(name = "asset_tag")
    private String assetTag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rack_id", nullable = false)
    private Rack rack;

    public RackAsset() {
    }

    public String getAssetTag() {
        return assetTag;
    }

    public void setAssetTag(String assetTag) {
        this.assetTag = assetTag;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Rack getRack() {
        return rack;
    }

    public void setRack(Rack rack) {
        this.rack = rack;
    }
}


