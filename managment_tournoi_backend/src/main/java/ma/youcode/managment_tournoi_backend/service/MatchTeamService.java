package ma.youcode.managment_tournoi_backend.service;

import ma.youcode.managment_tournoi_backend.entity.MatchTeam;

import java.util.List;

public interface MatchTeamService {
    MatchTeam createMatchTeam(MatchTeam matchTeam);
    List<MatchTeam> getAllMatchTeam();
    MatchTeam getMatchTeamById(int id);

}
