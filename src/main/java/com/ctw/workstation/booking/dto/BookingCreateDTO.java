package com.ctw.workstation.booking.dto;

import java.time.LocalDateTime;

public class BookingCreateDTO {
    private Long rackId;
    private Long requesterId;
    private LocalDateTime bookFrom;
    private LocalDateTime bookTo;

    public Long getRackId() {
        return rackId;
    }

    public void setRackId(Long rackId) {
        this.rackId = rackId;
    }

    public Long getRequesterId() {
        return requesterId;
    }

    public void setRequesterId(Long requesterId) {
        this.requesterId = requesterId;
    }

    public LocalDateTime getBookFrom() {
        return bookFrom;
    }

    public void setBookFrom(LocalDateTime bookFrom) {
        this.bookFrom = bookFrom;
    }

    public LocalDateTime getBookTo() {
        return bookTo;
    }

    public void setBookTo(LocalDateTime bookTo) {
        this.bookTo = bookTo;
    }


    public BookingCreateDTO(LocalDateTime bookFrom, LocalDateTime bookTo, Long rackId, Long requesterId) {
        this.bookFrom = bookFrom;
        this.bookTo = bookTo;
        this.rackId = rackId;
        this.requesterId = requesterId;
    }
}



