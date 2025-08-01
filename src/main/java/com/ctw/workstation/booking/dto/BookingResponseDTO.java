package com.ctw.workstation.booking.dto;

import net.bytebuddy.asm.Advice;

import java.time.Instant;
import java.time.LocalDateTime;

public class BookingResponseDTO {
    private Long id;
    private Long rackId;
    private Long requesterId;
    private LocalDateTime bookFrom;
    private LocalDateTime bookTo;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public BookingResponseDTO() {}

    public BookingResponseDTO(com.ctw.workstation.booking.entity.Booking booking) {
        this.id = booking.getId();
        this.rackId = booking.getRack() != null ? booking.getRack().getId() : null;
        this.requesterId = booking.getRequester() != null ? booking.getRequester().getId() : null;
        this.bookFrom = booking.getBookFrom();
        this.bookTo = booking.getBookTo();
        this.createdAt = booking.getCreatedAt();
        this.modifiedAt = booking.getModifiedAt();
    }

    public Long getId() {
        return id;
    }

    public  Long getRackId() {
        return rackId;
    }

    public Long getRequesterId() {
        return requesterId;
    }


    public  LocalDateTime getBookFrom() {
        return bookFrom;
    }

    public  LocalDateTime getBookTo() {
        return bookTo;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRackId(Long rackId) {
        this.rackId = rackId;
    }

    public void setRequesterId(Long requesterId) {
        this.requesterId = requesterId;
    }


    public void setBookFrom(LocalDateTime bookFrom) {
        this.bookFrom = bookFrom;
    }

    public void setBookTo(LocalDateTime bookTo) {
        this.bookTo = bookTo;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
}
