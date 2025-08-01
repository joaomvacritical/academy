    package com.ctw.workstation.rack.control;

    import com.ctw.workstation.rack.dto.RackCreateDTO;
    import com.ctw.workstation.rack.dto.RackResponseDTO;
    import com.ctw.workstation.rack.entity.Rack;
    import com.ctw.workstation.rack.entity.Rack.Status;
    import com.ctw.workstation.rack.repository.RackRepository;
    import jakarta.enterprise.context.ApplicationScoped;
    import jakarta.inject.Inject;
    import jakarta.transaction.Transactional;
    import jakarta.ws.rs.NotFoundException;
    import org.jboss.logging.Logger;

    import java.util.List;
    import java.util.Optional;
    import java.util.stream.Collectors;

    @ApplicationScoped
    public class RackService {

        @Inject
        RackRepository rackRepository;

        @Inject
        Logger log;

        @Transactional
        public RackResponseDTO create(RackCreateDTO dto) {
            validateRackDTO(dto);

            Status status = Status.valueOf(dto.getStatus().toUpperCase());

            Rack rack = new Rack();
            rack.setSerialNumber(dto.getSerialNumber());
            rack.setStatus(status);
            rack.setDefaultLocation(dto.getDefaultLocation());

            rackRepository.persist(rack);
            rackRepository.flush();

            log.infof("Rack created with id=%d", rack.getId());

            return new RackResponseDTO(rack);
        }

        public List<RackResponseDTO> listAll() {
            return rackRepository.listAll().stream()
                    .map(RackResponseDTO::new)
                    .collect(Collectors.toList());
        }

        public RackResponseDTO findById(Long id) {
            Rack rack = Optional.ofNullable(rackRepository.findById(id))
                    .orElseThrow(() -> new NotFoundException("Rack with ID: " + id + " not found."));
            return new RackResponseDTO(rack);
        }

        @Transactional
        public RackResponseDTO update(Long id, RackCreateDTO dto) {
            Rack existing = Optional.ofNullable(rackRepository.findById(id))
                    .orElseThrow(() -> new NotFoundException("Rack with ID: " + id + " not found."));

            Optional.ofNullable(dto.getSerialNumber())
                    .filter(s -> !s.isBlank())
                    .ifPresent(existing::setSerialNumber);

            Optional.ofNullable(dto.getStatus())
                    .filter(s -> !s.isBlank())
                    .ifPresent(s -> {
                        try {
                            Status status = Status.valueOf(s.toUpperCase());
                            existing.setStatus(status);
                            log.debugf("Update status to: %s", status);
                        } catch (IllegalArgumentException e) {
                            log.errorf("Invalid provided Status: %s", s);
                            throw new IllegalArgumentException("Invalid status value");
                        }
                    });

            Optional.ofNullable(dto.getDefaultLocation())
                    .filter(s -> !s.isBlank())
                    .ifPresent(existing::setDefaultLocation);

            log.infof("Rack updated with id=%d", id);

            return new RackResponseDTO(existing);
        }

        @Transactional
        public void delete(Long id) {
            boolean deleted = rackRepository.deleteById(id);
            if (!deleted) {
                throw new NotFoundException("Rack with ID: " + id + " not Found.");
            }
            log.infof("Rack with id=%d deleted with success", id);
        }

        private void validateRackDTO(RackCreateDTO dto) {
            if (dto.getSerialNumber() == null || dto.getSerialNumber().isBlank()) {
                throw new IllegalArgumentException("Serial number is required");
            }
            if (dto.getStatus() == null || dto.getStatus().isBlank()) {
                throw new IllegalArgumentException("Status is required");
            }
            try {
                Status.valueOf(dto.getStatus().toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid status value");
            }
        }


        @Transactional
        public RackResponseDTO updateStatus(Long id, Rack.Status status) {
            Rack rack = Optional.ofNullable(rackRepository.findById(id))
                    .orElseThrow(() -> new NotFoundException("Rack with ID: " + id + " not found."));

            rack.setStatus(status); // JPA will detect this change

            return new RackResponseDTO(rack);
        }



    }

