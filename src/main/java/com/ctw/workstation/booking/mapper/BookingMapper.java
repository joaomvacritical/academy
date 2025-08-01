package com.ctw.workstation.booking.mapper;

import com.ctw.workstation.booking.dto.BookingCreateDTO;
import com.ctw.workstation.booking.dto.BookingResponseDTO;
import com.ctw.workstation.booking.dto.BookingUpdateDTO;
import com.ctw.workstation.booking.entity.Booking;
import com.ctw.workstation.rack.entity.Rack;
import com.ctw.workstation.teammember.entity.TeamMember;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BookingMapper {

    public Booking toEntity(BookingCreateDTO dto, Rack rack, TeamMember requester) {
        Booking booking = new Booking();
        booking.setRack(rack);
        booking.setRequester(requester);
        booking.setBookFrom(dto.getBookFrom());
        booking.setBookTo(dto.getBookTo());
        return booking;
    }

    public BookingResponseDTO toResponseDTO(Booking booking) {
        return new BookingResponseDTO(booking);
    }

    public void updateEntity(Booking existing, BookingUpdateDTO dto, Rack rack, TeamMember requester) {
        if (rack != null) {
            existing.setRack(rack);
        }

        if (requester != null) {
            existing.setRequester(requester);
        }

        if (dto.getBookFrom() != null) {
            existing.setBookFrom(dto.getBookFrom());
        }

        if (dto.getBookTo() != null) {
            existing.setBookTo(dto.getBookTo());
        }
    }
}
