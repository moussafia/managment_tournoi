package ma.youcode.managment_tournoi_backend.service;

import ma.youcode.managment_tournoi_backend.entity.Group;
import ma.youcode.managment_tournoi_backend.entity.TeamGroup;

import java.util.List;
import java.util.Optional;

public interface TeamGroupService {
    void createGroupTeam();
    void deleteALLGroupTeam();
    TeamGroup getGroupTeamById(Long groupId);
    List<TeamGroup> getAllGroupTeam();
    List<TeamGroup> calculRanksTeamGroup();
    }
