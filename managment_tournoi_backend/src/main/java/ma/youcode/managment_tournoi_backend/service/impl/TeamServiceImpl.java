package ma.youcode.managment_tournoi_backend.service.impl;

import lombok.RequiredArgsConstructor;
import ma.youcode.managment_tournoi_backend.entity.Team;
import ma.youcode.managment_tournoi_backend.exception.EntityNotFoundException;
import ma.youcode.managment_tournoi_backend.repository.TeamRepository;
import ma.youcode.managment_tournoi_backend.service.TeamService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;
    @Override
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    @Override
    public Team getTeamById(Long id) {

        return teamRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Team with ID " + id + " not found"));
    }

    @Override
    public Team createTeam(Team team) {
        return null;
    }

    @Override
    public Team updateTeam(Team team) {
        return null;
    }
}
