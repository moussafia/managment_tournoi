package ma.youcode.managment_tournoi_backend.util.team;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import ma.youcode.managment_tournoi_backend.entity.Team;
import ma.youcode.managment_tournoi_backend.exception.EntityAlreadyExistException;
import ma.youcode.managment_tournoi_backend.repository.TeamRepository;
import org.springframework.stereotype.Component;

@Component
public class TeamUtils {
    private static TeamRepository teamRepository;

    public TeamUtils(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public static void validateIfNameOfTeamAlreadyExistsForCreate(Team team) {
        teamRepository.findByNameTeam(team.getNameTeam()).ifPresent(existingTeam -> {
            throw new EntityAlreadyExistException("Team with name " + existingTeam.getNameTeam() + " already exists");
        });
    }
    public static void validateIfNameOfTeamAlreadyExistsForUpdate(Team team) {
//            teamRepository.findByNameTeam(team.getNameTeam()).ifPresent(existingTeam -> {
//            if(!existingTeam.getId().equals(team.getId())) {
//                throw new EntityAlreadyExistException("Team with name " + existingTeam.getNameTeam() + " already exists");
//            }
//        });
    }
}
