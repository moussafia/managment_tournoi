package ma.youcode.managment_tournoi_backend.service;

import ma.youcode.managment_tournoi_backend.entity.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface TeamService {
    Page<Team> getAllTeams(Pageable pageable);
    Team getTeamById(UUID id);
    Team createTeam(Team team, MultipartFile image);
    Team updateTeam(Team team, MultipartFile image);
    void deleteTeam(UUID teamId);
}
