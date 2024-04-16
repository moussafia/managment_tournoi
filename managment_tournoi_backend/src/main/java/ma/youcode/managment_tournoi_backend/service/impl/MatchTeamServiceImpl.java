package ma.youcode.managment_tournoi_backend.service.impl;

import lombok.RequiredArgsConstructor;
import ma.youcode.managment_tournoi_backend.entity.Match;
import ma.youcode.managment_tournoi_backend.entity.MatchTeam;
import ma.youcode.managment_tournoi_backend.entity.Team;
import ma.youcode.managment_tournoi_backend.entity.embedded.MatchTeamEmbeddedId;
import ma.youcode.managment_tournoi_backend.entity.enums.LevelEnum;
import ma.youcode.managment_tournoi_backend.repository.MatchTeamRepository;
import ma.youcode.managment_tournoi_backend.service.MatchService;
import ma.youcode.managment_tournoi_backend.service.MatchTeamService;
import ma.youcode.managment_tournoi_backend.service.TeamService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class MatchTeamServiceImpl implements MatchTeamService {
    private final MatchTeamRepository matchTeamRepository;
    private final TeamService teamService;
    private final MatchService matchService;
    @Override
    public MatchTeam createMatchTeam(UUID matchId, UUID teamId, LevelEnum levelEnum) {
        Team team = teamService.getTeamById(teamId);
        Match match = matchService.getMatchById(matchId);
        MatchTeamEmbeddedId matchTeamEmbeddedId = new MatchTeamEmbeddedId(matchId, teamId);
        MatchTeam matchTeam = new MatchTeam().builder()
                .id(matchTeamEmbeddedId)
                .isDraw(false)
                .level(levelEnum)
                .isPassed(false)
                .team(team)
                .match(match)
                .result(0)
                .isWin(false).build();

        return matchTeamRepository.save(matchTeam);
    }

    @Override
    public List<MatchTeam> getAllMatchTeam() {
        return List.of();
    }

    @Override
    public MatchTeam getMatchTeamById(int id) {
        return null;
    }
}
