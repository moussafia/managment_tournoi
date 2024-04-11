package ma.youcode.managment_tournoi_backend.service.impl;

import lombok.RequiredArgsConstructor;
import ma.youcode.managment_tournoi_backend.entity.AppUser;
import ma.youcode.managment_tournoi_backend.entity.Participant;
import ma.youcode.managment_tournoi_backend.entity.Team;
import ma.youcode.managment_tournoi_backend.repository.ParticipentRepository;
import ma.youcode.managment_tournoi_backend.service.AppUserService;
import ma.youcode.managment_tournoi_backend.service.ParticipentService;
import ma.youcode.managment_tournoi_backend.service.TeamService;
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
    @Override
    public List<Participant> createParticipant(List<UUID> usersIds, Team team, MultipartFile logo) {
        Team teamToCreate = teamService.createTeam(team, logo);
        List<Participant> participants = new ArrayList<>();
        for (UUID id : usersIds) {
            AppUser member = appUserService.findMemberById(id);
            ParticipantUtils.validateDateMemberShip(member, teamToCreate);
            Participant participant = new Participant().builder().team(teamToCreate).user(member).build();
            participants.add(participant);
        }
        return participentRepository.saveAll(participants);
    }

    @Override
    public List<Participant> updateParticipant(List<UUID> usersIds, Team team, MultipartFile logo) {
        Team teamToUpdate = teamService.updateTeam(team, logo);
        List<Participant> participants = new ArrayList<>();
        for (UUID id : usersIds) {
            AppUser member = appUserService.findMemberById(id);
            ParticipantUtils.validateDateMemberShip(member, teamToUpdate);
            Participant participant = new Participant().builder().team(teamToUpdate).user(member).build();
            participants.add(participant);
        }
        return participentRepository.saveAll(participants);
    }

    @Override
    public void deleteParticipant(UUID teamId) {
        teamService.deleteTeam(teamId);
    }
}
