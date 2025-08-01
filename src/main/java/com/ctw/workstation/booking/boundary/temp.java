/*
package com.ctw.workstation.booking.boundary;

import com.ctw.workstation.booking.dto.BookingCreateDTO;
import com.ctw.workstation.booking.dto.BookingResponseDTO;
import com.ctw.workstation.booking.dto.BookingUpdateDTO;
import com.ctw.workstation.booking.entity.Booking;
import com.ctw.workstation.booking.mapper.BookingMapper;
import com.ctw.workstation.booking.repository.BookingRepository;
import com.ctw.workstation.rack.entity.Rack;
import com.ctw.workstation.rack.repository.RackRepository;
import com.ctw.workstation.teammember.entity.TeamMember;
import com.ctw.workstation.teammember.repository.TeamMemberRepository;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;

import java.util.List;
import java.util.stream.Collectors;

@Path("/workstation/bookings")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BookingResource {

    @Inject
    Logger log;

    @Inject
    BookingRepository bookingRepository;

    @Inject
    RackRepository rackRepository;

    @Inject
    TeamMemberRepository teamMemberRepository;

    @POST
    @Transactional
    public Response createBooking(BookingCreateDTO dto) {
        log.infof("Creating booking for rackId=%d and requesterId=%d", dto.getRackId(), dto.getRequesterId());

        Rack rack = rackRepository.findById(dto.getRackId());
        if (rack == null) {
            log.warnf("Rack with ID %d not found", dto.getRackId());
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Rack com ID " + dto.getRackId() + " não encontrado.")
                    .build();
        }

        TeamMember requester = teamMemberRepository.findById(dto.getRequesterId());
        if (requester == null) {
            log.warnf("Requester with ID %d not found", dto.getRequesterId());
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Requester com ID " + dto.getRequesterId() + " não encontrado.")
                    .build();
        }

        Booking booking = BookingMapper.toEntity(dto, rack, requester);
        bookingRepository.persist(booking);
        bookingRepository.flush();

        log.infof("Booking created with ID %d", booking.getId());

        return Response.status(Response.Status.CREATED)
                .entity(BookingMapper.toDTO(booking))
                .build();
    }

    @GET
    public Response listBookings() {
        log.info("Listing all bookings");
        List<BookingResponseDTO> dtos = bookingRepository.listAll().stream()
                .map(BookingMapper::toDTO)
                .collect(Collectors.toList());

        return Response.ok(dtos).build();
    }

    @GET
    @Path("/{id}")
    public Response getBooking(@PathParam("id") Long id) {
        log.infof("Retrieving booking with ID %d", id);
        Booking booking = bookingRepository.findById(id);
        if (booking == null) {
            log.warnf("Booking with ID %d not found", id);
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(BookingMapper.toDTO(booking)).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateBooking(@PathParam("id") Long id, BookingUpdateDTO dto) {
        log.infof("Updating booking with ID %d", id);

        Booking existing = bookingRepository.findById(id);
        if (existing == null) {
            log.warnf("Booking with ID %d not found", id);
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        Rack rack = null;
        if (dto.getRackId() != null) {
            rack = rackRepository.findById(dto.getRackId());
            if (rack == null) {
                log.warnf("Rack with ID %d not found", dto.getRackId());
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Rack com ID " + dto.getRackId() + " não encontrado.")
                        .build();
            }
        }

        TeamMember requester = null;
        if (dto.getRequesterId() != null) {
            requester = teamMemberRepository.findById(dto.getRequesterId());
            if (requester == null) {
                log.warnf("Requester with ID %d not found", dto.getRequesterId());
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Requester com ID " + dto.getRequesterId() + " não encontrado.")
                        .build();
            }
        }

        BookingMapper.updateEntity(existing, dto, rack, requester);

        log.infof("Booking with ID %d updated successfully", id);
        return Response.ok(BookingMapper.toDTO(existing)).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteBooking(@PathParam("id") Long id) {
        log.infof("Deleting booking with ID %d", id);
        boolean deleted = bookingRepository.deleteById(id);
        if (!deleted) {
            log.warnf("Booking with ID %d not found for deletion", id);
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        log.infof("Booking with ID %d deleted", id);
        return Response.noContent().build();
    }
}
*/
