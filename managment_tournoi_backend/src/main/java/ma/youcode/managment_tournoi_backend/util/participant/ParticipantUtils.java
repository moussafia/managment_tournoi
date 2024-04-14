package ma.youcode.managment_tournoi_backend.util.participant;

import lombok.AllArgsConstructor;
import ma.youcode.managment_tournoi_backend.entity.AppUser;
import ma.youcode.managment_tournoi_backend.entity.Participant;
import ma.youcode.managment_tournoi_backend.entity.Team;
import ma.youcode.managment_tournoi_backend.entity.embedded.ParticipantEmbeddedId;
import ma.youcode.managment_tournoi_backend.repository.ParticipentRepository;
import ma.youcode.managment_tournoi_backend.service.AppUserService;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class ParticipantUtils {
    private static ParticipentRepository participentRepository;
    public  static AppUserService appUserService;
    public ParticipantUtils(ParticipentRepository participentRepository, AppUserService appUserService) {
        this.participentRepository = participentRepository;
        this.appUserService = appUserService;
    }

    public static void validateNumberOfParticipent(List<UUID> usersIds, Integer numberOfParticipants) {
        if (usersIds.size() > numberOfParticipants) {
            throw new IllegalArgumentException("Number of participants is greater than the number of participants");
        }
    }

    public static void validateUsersIdsAlreadyExist(List<UUID> usersIds) {
        for(UUID id : usersIds){
            appUserService.findMemberById(id);
        }
    }

    public static List<Participant> createListOfParticipent(List<UUID> usersIds, Team team) {
        List<Participant> participants = new ArrayList<>();
        for (UUID id : usersIds) {
            AppUser member = appUserService.findMemberById(id);
            ParticipantEmbeddedId participantEmbeddedId = new ParticipantEmbeddedId(team.getId(), id);
            Participant participant = new Participant().builder().user(member).team(team).build();
            participant.setParticipantEmbeddedId(participantEmbeddedId);
            participant.setDateOfCreation(LocalDateTime.now());
            participants.add(participant);
        }
        return participants;
    }
    public static void validateDateMemberShipList(List<UUID> usersIds){
        for (UUID id : usersIds) {
            validateDateMemberShip(id);
        }

    }
    private static void validateDateMemberShip(UUID userId){
        Participant participant = participentRepository.findByUserIdOrderByDateOfCreationDesc(userId).orElse(null);
        if (participant != null && !participant.getDateOfCreation().plusMonths(3).isBefore(LocalDateTime.now())){
            throw new RuntimeException("A participant with Id " + userId +
                    " should enroll in a team named "
                    +  participant.getTeam().getNameTeam() + " after a period of three months from the current date.");
        }
    }
}
