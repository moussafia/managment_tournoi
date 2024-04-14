package ma.youcode.managment_tournoi_backend.service;

import ma.youcode.managment_tournoi_backend.entity.Team;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface TeamService {
    List<Team> getAllTeams();
    Team getTeamById(UUID id);
    Team createTeam(Team team, MultipartFile image);
    Team updateTeam(Team team, MultipartFile image);
    void deleteTeam(UUID teamId);
}
