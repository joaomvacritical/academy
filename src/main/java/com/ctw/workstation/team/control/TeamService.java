package com.ctw.workstation.team.control;

import com.ctw.workstation.team.dto.TeamDTO;
import com.ctw.workstation.team.dto.TeamRequestDTO;
import com.ctw.workstation.team.entity.Team;
import com.ctw.workstation.team.mapper.TeamMapper;
import com.ctw.workstation.team.repository.TeamRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.jboss.logging.Logger;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class TeamService {

    @Inject
    TeamRepository teamRepository;

    @Inject
    Logger log;

    public List<TeamDTO> getAll() {
        log.debug("A carregar todas as equipas");
        return teamRepository.findAll().stream()
                .map(TeamMapper::toDTO)
                .collect(Collectors.toList());
    }

    public TeamDTO getById(Long id) {
        log.debugf("A procurar equipa com id=%d", id);
        Team team = findByIdOrThrow(id);
        return TeamMapper.toDTO(team);
    }

    @Transactional
    public TeamDTO create(TeamRequestDTO dto) {
        log.debugf("A criar nova equipa: %s", dto.getName());
        Team team = TeamMapper.toEntity(dto);
        teamRepository.persist(team);
        teamRepository.flush();
        log.infof("Equipa criada com sucesso: id=%d", team.getId());
        return TeamMapper.toDTO(team);
    }

    @Transactional
    public TeamDTO update(Long id, TeamRequestDTO dto) {
        log.debugf("A atualizar equipa com id=%d", id);
        Team team = findByIdOrThrow(id);
        TeamMapper.updateEntity(team, dto);
        log.infof("Equipa com id=%d atualizada com sucesso", id);
        return TeamMapper.toDTO(team);
    }

    @Transactional
    public void delete(Long id) {
        log.debugf("A tentar apagar equipa com id=%d", id);
        Team team = findByIdOrThrow(id);
        teamRepository.delete(team);
        log.infof("Equipa com id=%d apagada com sucesso", id);
    }

    private Team findByIdOrThrow(Long id) {
        return teamRepository.findById(id);
    }
}
