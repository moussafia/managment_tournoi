package ma.youcode.managment_tournoi_backend.service;

import ma.youcode.managment_tournoi_backend.entity.MatchTeam;
import ma.youcode.managment_tournoi_backend.entity.enums.LevelEnum;

import java.util.List;
import java.util.UUID;

public interface MatchTeamService {
    MatchTeam createMatchTeam(UUID matchId, UUID teamId, LevelEnum levelEnum);
    List<MatchTeam> getAllMatchTeam();
    MatchTeam getMatchTeamById(int id);

}
