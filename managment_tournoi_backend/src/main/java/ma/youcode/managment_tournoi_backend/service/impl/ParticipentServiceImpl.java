package ma.youcode.managment_tournoi_backend.service.impl;

import lombok.RequiredArgsConstructor;
import ma.youcode.managment_tournoi_backend.entity.AppUser;
import ma.youcode.managment_tournoi_backend.entity.Participant;
import ma.youcode.managment_tournoi_backend.entity.Team;
import ma.youcode.managment_tournoi_backend.repository.ParticipentRepository;
import ma.youcode.managment_tournoi_backend.service.AppUserService;
import ma.youcode.managment_tournoi_backend.service.ParticipentService;
import ma.youcode.managment_tournoi_backend.service.TeamService;
import ma.youcode.managment_tournoi_backend.util.image.ImageUtils;
import ma.youcode.managment_tournoi_backend.util.participant.ParticipantUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class ParticipentServiceImpl implements ParticipentService {
    private final ParticipentRepository participentRepository;
    private final TeamService teamService;
    private final AppUserService appUserService;
    private final ImageUtils imageUtils;

    @Override
    public List<Participant> createParticipant(List<UUID> usersIds, Team team, MultipartFile logo, Integer numberOfParticipants) {
        ParticipantUtils.validateNumberOfParticipent(usersIds, numberOfParticipants);
        ParticipantUtils.validateUsersIdsAlreadyExist(usersIds);
        Team teamToCreate = teamService.createTeam(team, logo);
        List<Participant> participants = ParticipantUtils.createListOfParticipent(usersIds, teamToCreate);;
        return participentRepository.saveAll(participants);
    }

    @Override
    public List<Participant> updateParticipant(List<UUID> usersIds, Team team, MultipartFile logo, Integer numberOfParticipants) {
        ParticipantUtils.validateNumberOfParticipent(usersIds, numberOfParticipants);
        ParticipantUtils.validateUsersIdsAlreadyExist(usersIds);
        Team teamToUpdate = teamService.updateTeam(team, logo);
        participentRepository.deleteAllByTeamId(teamToUpdate.getId());
        List<Participant> participants = ParticipantUtils.createListOfParticipent(usersIds, teamToUpdate);;
        return participentRepository.saveAll(participants);
    }

    @Override
    public void deleteParticipant(UUID teamId, String publicIdLogo) {
        imageUtils.deleteImage(publicIdLogo);
        teamService.deleteTeam(teamId);
    }

}
