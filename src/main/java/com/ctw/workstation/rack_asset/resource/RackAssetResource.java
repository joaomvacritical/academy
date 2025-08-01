package com.ctw.workstation.rack_asset.resource;

import com.ctw.workstation.rack.entity.Rack;
import com.ctw.workstation.rack.repository.RackRepository;
import com.ctw.workstation.rack_asset.dto.RackAssetCreateDTO;
import com.ctw.workstation.rack_asset.dto.RackAssetResponseDTO;
import com.ctw.workstation.rack_asset.dto.RackAssetUpdateDTO;
import com.ctw.workstation.rack_asset.entity.RackAsset;
import com.ctw.workstation.rack_asset.repository.RackAssetRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.stream.Collectors;

@Path("/workstation/rack-assets")

public class RackAssetResource {

    @Inject
    RackAssetRepository rackAssetRepository;

    @Inject
    RackRepository rackRepository;

    @POST
    @Transactional
    public Response createRackAsset(RackAssetCreateDTO dto) {
        if (dto.getAssetTag() == null || dto.getAssetTag().isBlank()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("assetTag is mandatory")
                    .build();
        }
        if (dto.getRackId() == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("rackId is mandatory")
                    .build();
        }

        Rack rack = rackRepository.findById(dto.getRackId());
        if (rack == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Rack with ID: " + dto.getRackId() + " not found.")
                    .build();
        }

        RackAsset asset = new RackAsset();
        asset.setAssetTag(dto.getAssetTag());
        asset.setRack(rack);

        rackAssetRepository.persist(asset);

        return Response.status(Response.Status.CREATED).entity(new RackAssetResponseDTO(asset)).build();
    }

    @GET
    public Response listRackAssets() {
        List<RackAssetResponseDTO> dtos = rackAssetRepository.listAll()
                .stream()
                .map(RackAssetResponseDTO::new)
                .collect(Collectors.toList());

        return Response.ok(dtos).build();
    }

    @GET
    @Path("/{id}")
    public Response getRackAsset(@PathParam("id") Long id) {
        RackAsset rackAsset = rackAssetRepository.findById(id);
        if (rackAsset == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(new RackAssetResponseDTO(rackAsset)).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateRackAsset(@PathParam("id") Long id, RackAssetUpdateDTO dto) {
        RackAsset existing = rackAssetRepository.findById(id);
        if (existing == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        if (dto.getAssetTag() != null && !dto.getAssetTag().isBlank()) {
            existing.setAssetTag(dto.getAssetTag());
        }

        if (dto.getRackId() != null) {
            Rack rack = rackRepository.findById(dto.getRackId());
            if (rack == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Rack with ID " + dto.getRackId() + " not found.")
                        .build();
            }
            existing.setRack(rack);
        }

        return Response.ok(new RackAssetResponseDTO(existing)).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteRackAsset(@PathParam("id") Long id) {
        boolean deleted = rackAssetRepository.deleteById(id);
        if (!deleted) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.noContent().build();
    }
}
