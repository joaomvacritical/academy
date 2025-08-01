package com.ctw.workstation.rack_asset.repository;


import com.ctw.workstation.rack_asset.entity.RackAsset;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

    @ApplicationScoped
    public class RackAssetRepository implements PanacheRepository<RackAsset> {
}

