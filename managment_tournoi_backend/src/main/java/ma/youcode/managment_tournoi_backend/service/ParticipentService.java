package ma.youcode.managment_tournoi_backend.service;

import ma.youcode.managment_tournoi_backend.entity.AppUser;
import ma.youcode.managment_tournoi_backend.entity.Participant;
import ma.youcode.managment_tournoi_backend.entity.Team;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface ParticipentService  {
    List<Participant> createParticipant(List<UUID> usersIds, Team team, MultipartFile logo, Integer numberOfParticipants);
    List<Participant> updateParticipant(List<UUID> usersIds, Team team, MultipartFile logo, Integer numberOfParticipants);
    void deleteParticipant(UUID teamId, String publicIdLogo);
}
