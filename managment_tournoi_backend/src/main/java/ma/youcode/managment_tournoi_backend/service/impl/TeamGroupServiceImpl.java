package ma.youcode.managment_tournoi_backend.service.impl;

import lombok.RequiredArgsConstructor;
import ma.youcode.managment_tournoi_backend.entity.Group;
import ma.youcode.managment_tournoi_backend.entity.Team;
import ma.youcode.managment_tournoi_backend.entity.enums.GroupEnum;
import ma.youcode.managment_tournoi_backend.exception.EntityAlreadyExistException;
import ma.youcode.managment_tournoi_backend.exception.EntityNotFoundException;
import ma.youcode.managment_tournoi_backend.repository.TeamGroupRepository;
import ma.youcode.managment_tournoi_backend.service.GroupService;
import ma.youcode.managment_tournoi_backend.service.TeamGroupService;
import ma.youcode.managment_tournoi_backend.service.TeamService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import ma.youcode.managment_tournoi_backend.entity.TeamGroup;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class TeamGroupServiceImpl implements TeamGroupService {
    private final TeamGroupRepository teamGroupRepository;
    private final TeamService teamService;
    private final GroupService groupService;
    @Override
    public void createGroupTeam() {
        List<Team> teamList = teamService.getLatestCreatedTeam();
        checkIfGroupAlreadyExist(teamList.get(0).getId());
        int teamsCount = teamList.size();
        int nbreOfGroup = (int) Math.ceil((double) teamsCount /4);
        int nbreOfTeamPerGroup = (int) Math.ceil((double) teamsCount / nbreOfGroup);
        for(int i = 0; i < nbreOfGroup; i++) {
            List<TeamGroup> groupList = new ArrayList<>();
            int startIndex = i * nbreOfTeamPerGroup;
            int endIndex = Math.min((i+1) * nbreOfTeamPerGroup, teamsCount - 1);
            List<Team> teams = teamList.subList(startIndex, endIndex);
            Group group = groupService.addGroup(new Group(null, GroupEnum.values()[i].name(), null));
            teams.forEach(t -> {
                TeamGroup teamGrps = new TeamGroup()
                        .builder()
                        .team(t)
                        .group(group)
                        .draws(0)
                        .wins(0)
                        .rank(1000)
                        .isPassed(false).losses(0).points(0)
                        .build();
                groupList.add(teamGrps);
            });
            List<TeamGroup> teamGroups = teamGroupRepository.saveAll(groupList);
            group.setTeamGroups(teamGroups);
        }
    }

    @Override
    public void deleteALLGroupTeam() {
        groupService.deleteAll();
    }

    @Override
    public TeamGroup getGroupTeamById(Long groupId) {
        return teamGroupRepository.findById(groupId)
                .orElseThrow(() -> new EntityNotFoundException("team group not found "));
    }
    @Override
    public List<TeamGroup> getAllGroupTeam() {
        return teamGroupRepository.findAll();
    }

    @Override
    public List<TeamGroup> calculRanksTeamGroup() {
        return List.of();
    }
    private void checkIfGroupAlreadyExist(UUID id) {
        teamGroupRepository.findByTeam_Id(id).ifPresent((teamGroup) -> {
            throw  new EntityAlreadyExistException("group already exist, please delete first and recreate it");});
    }

}
