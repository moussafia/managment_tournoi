package ma.youcode.managment_tournoi_backend.service.impl;

import lombok.RequiredArgsConstructor;
import ma.youcode.managment_tournoi_backend.entity.Group;
import ma.youcode.managment_tournoi_backend.entity.Team;
import ma.youcode.managment_tournoi_backend.entity.enums.GroupEnum;
import ma.youcode.managment_tournoi_backend.exception.EntityNotFoundException;
import ma.youcode.managment_tournoi_backend.repository.TeamGroupRepository;
import ma.youcode.managment_tournoi_backend.service.GroupService;
import ma.youcode.managment_tournoi_backend.service.TeamGroupService;
import ma.youcode.managment_tournoi_backend.service.TeamService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import ma.youcode.managment_tournoi_backend.entity.TeamGroup;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class    TeamGroupServiceImpl implements TeamGroupService {
    private final TeamGroupRepository teamGroupRepository;
    private final TeamService teamService;
    private final GroupService groupService;
    @Override
    public List<TeamGroup> createGroupTeam() {
        List<Team> teamList = teamService.getLatestCreatedTeam();
        int teamsCount = teamList.size();
        int nbreOfGroup = (int) Math.ceil((double) teamsCount /4);
        int nbreOfTeamPerGroup = (int) Math.ceil((double) teamsCount / nbreOfGroup);
        List<TeamGroup> groupList = new ArrayList<>();
        for(int i = 0; i < nbreOfGroup; i++) {
            int startIndex = i * nbreOfTeamPerGroup;
            int endIndex = Math.min((i+1) * nbreOfTeamPerGroup, teamsCount - 1);
            List<Team> teams = teamList.subList(startIndex, endIndex);
            Group group = groupService.addGroup(new Group(null, GroupEnum.values()[i].name(), null));
            teams.forEach(t -> {
                TeamGroup teamGrps = new TeamGroup()
                        .builder()
                        .team(t)
                        .group(group)
                        .build();
                groupList.add(teamGrps);
            });
        }
        return teamGroupRepository.saveAll(groupList);
    }

    @Override
    public void deleteALLGroupTeam() {
        groupService.deleteAll();
        teamGroupRepository.deleteAll();
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

}
