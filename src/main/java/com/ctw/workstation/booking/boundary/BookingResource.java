package com.ctw.workstation.booking.boundary;

import com.ctw.workstation.booking.control.BookingService;
import com.ctw.workstation.booking.dto.BookingCreateDTO;
import com.ctw.workstation.booking.dto.BookingResponseDTO;
import com.ctw.workstation.booking.dto.BookingUpdateDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;

import java.util.List;

@Path("/workstation/bookings")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BookingResource {

    private static Logger log = Logger.getLogger(BookingResource.class);

    @Inject
    BookingService bookingService;

    @POST
    public Response createBooking(BookingCreateDTO dto) {
        log.info("POST request received to create a booking");
        BookingResponseDTO response = bookingService.create(dto);

        return Response.status(Response.Status.CREATED).entity(response).build();
    }

    @GET
    public Response listBookings() {
        log.info("GET request received to list bookings");
        List<BookingResponseDTO> responseList = bookingService.listAll();
        return Response.ok(responseList).build();
    }

    @GET
    @Path("/{id}")
    public Response getBooking(@PathParam("id") Long id) {
        log.infof("GET request received to obtain a booking with id=%d", id);
        BookingResponseDTO response = bookingService.findById(id);
        return Response.ok(response).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateBooking(@PathParam("id") Long id, BookingUpdateDTO dto) {
        log.infof("PUT request received to update a booking with id=%d", id);
        BookingResponseDTO response = bookingService.update(id, dto);
        return Response.ok(response).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteBooking(@PathParam("id") Long id) {
        log.infof("DELETE request received to delete a booking with id=%d", id);
        bookingService.delete(id);
        return Response.noContent().build();
    }
}
