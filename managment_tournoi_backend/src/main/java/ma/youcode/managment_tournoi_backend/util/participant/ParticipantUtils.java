package ma.youcode.managment_tournoi_backend.util.participant;

import lombok.AllArgsConstructor;
import ma.youcode.managment_tournoi_backend.entity.AppUser;
import ma.youcode.managment_tournoi_backend.entity.Participant;
import ma.youcode.managment_tournoi_backend.entity.Team;
import ma.youcode.managment_tournoi_backend.repository.ParticipentRepository;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class ParticipantUtils {
    private static ParticipentRepository participentRepository;
    public static void validateDateMemberShip(AppUser appUser, Team team){
        Participant participant = participentRepository.findByTeamAndUser(team, appUser).get();
        if (participant != null && participant.getDateOfCreation().plusMonths(3).isBefore(LocalDateTime.now())){
            throw new RuntimeException("A participant named " + appUser.getLastName() +
                    " should enroll in a team named "
                    +  team.getNameTeam() + " after a period of three months from the current date.");
        }
    }
}
