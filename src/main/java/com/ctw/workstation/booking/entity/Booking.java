package com.ctw.workstation.booking.entity;

import com.ctw.workstation.rack.entity.Rack;
import com.ctw.workstation.teammember.entity.TeamMember;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "t_booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "booking_seq_gen")
    @SequenceGenerator(name = "booking_seq_gen", sequenceName = "booking_seq", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rack_id", nullable = false)
    private Rack rack;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "requester_id", nullable = false)
    private TeamMember requester;

    @Column(name = "book_from", nullable = false)
    private LocalDateTime bookFrom;

    @Column(name = "book_to", nullable = false)
    private LocalDateTime bookTo;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    @UpdateTimestamp
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime modifiedAt;

    public Booking() {}

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Rack getRack() { return rack; }
    public void setRack(Rack rack) { this.rack = rack; }

    public TeamMember getRequester() { return requester; }
    public void setRequester(TeamMember requester) { this.requester = requester; }

    public LocalDateTime getBookFrom() { return bookFrom; }
    public void setBookFrom(LocalDateTime bookFrom) { this.bookFrom = bookFrom; }

    public LocalDateTime getBookTo() { return bookTo; }
    public void setBookTo(LocalDateTime bookTo) { this.bookTo = bookTo; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getModifiedAt() { return modifiedAt; }
}
