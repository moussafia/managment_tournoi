package ma.youcode.managment_tournoi_backend.service;

import ma.youcode.managment_tournoi_backend.entity.Match;
import ma.youcode.managment_tournoi_backend.entity.MatchTeam;
import ma.youcode.managment_tournoi_backend.entity.enums.LevelEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface MatchTeamService {
    List<MatchTeam> createMatchTeam(Match match, List<UUID> teamId, UUID arbitraire, LevelEnum levelEnum);
    Page<MatchTeam> getAllMatchTeam(Pageable pageable);
    List<MatchTeam> getMatchTeamByTeamId(UUID teamId);
    List<MatchTeam> updateMatchTeam(Match match, List<UUID> teamId, UUID arbitraire, LevelEnum levelEnum);
    List<MatchTeam> getMatchTeamByMatchId(UUID matchId);
}
