package com.ctw.workstation.booking.control;

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
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.jboss.logging.Logger;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class BookingService {

    @Inject
    Logger log;

    @Inject
    BookingRepository bookingRepository;

    @Inject
    RackRepository rackRepository;

    @Inject
    TeamMemberRepository teamMemberRepository;

    @Inject
    BookingMapper bookingMapper;

    @Transactional
    public BookingResponseDTO create(BookingCreateDTO dto) {
        log.debug("Início da criação de booking");
        Rack rack = findRackOrThrow(dto.getRackId());
        TeamMember requester = findTeamMemberOrThrow(dto.getRequesterId());

        Booking booking = bookingMapper.toEntity(dto, rack, requester);
        bookingRepository.persist(booking);
        rack.setStatus(Rack.Status.BOOKED);

        log.infof("Booking criado com sucesso: id=%d", booking.getId());
        return bookingMapper.toResponseDTO(booking);
    }

    public List<BookingResponseDTO> listAll() {
        log.debug("A carregar todos os bookings");
        return bookingRepository.listAll().stream()
                .map(bookingMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public BookingResponseDTO findById(Long id) {
        log.debugf("A procurar booking com id=%d", id);
        Booking booking = findBookingOrThrow(id);
        return bookingMapper.toResponseDTO(booking);
    }

    @Transactional
    public BookingResponseDTO update(Long id, BookingUpdateDTO dto) {
        log.debugf("Início da atualização do booking com id=%d", id);

        Booking existing = findBookingOrThrow(id);

        Rack rack = Optional.ofNullable(dto.getRackId())
                .map(this::findRackOrThrow)
                .orElse(null);

        TeamMember requester = Optional.ofNullable(dto.getRequesterId())
                .map(this::findTeamMemberOrThrow)
                .orElse(null);

        bookingMapper.updateEntity(existing, dto, rack, requester);

        log.infof("Booking updated with sucess: id=%d", existing.getId());
        return bookingMapper.toResponseDTO(existing);
    }

    @Transactional
    public void delete(Long id) {
        log.debugf("A tentar apagar booking com id=%d", id);
        boolean deleted = bookingRepository.deleteById(id);
        if (!deleted) {
            log.warnf("Booking com id=%d não encontrado para apagar", id);
            throw new NotFoundException("Booking com ID " + id + " não encontrado.");
        }
        log.infof("Booking com id=%d apagado com sucesso", id);
    }

    private Rack findRackOrThrow(Long id) {
        return Optional.ofNullable(rackRepository.findById(id))
                .orElseThrow(() -> {
                    log.warnf("Rack com id=%d não encontrado", id);
                    return new NotFoundException("Rack com ID " + id + " não encontrado.");
                });
    }

    private TeamMember findTeamMemberOrThrow(Long id) {
        return Optional.ofNullable(teamMemberRepository.findById(id))
                .orElseThrow(() -> {
                    log.warnf("Requester com id=%d não encontrado", id);
                    return new NotFoundException("Requester com ID " + id + " não encontrado.");
                });
    }

    private Booking findBookingOrThrow(Long id) {
        return Optional.ofNullable(bookingRepository.findById(id))
                .orElseThrow(() -> {
                    log.warnf("Booking com id=%d não encontrado", id);
                    return new NotFoundException("Booking com ID " + id + " não encontrado.");
                });
    }
}
