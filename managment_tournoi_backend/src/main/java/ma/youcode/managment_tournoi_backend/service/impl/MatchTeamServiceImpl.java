package ma.youcode.managment_tournoi_backend.service.impl;

import lombok.RequiredArgsConstructor;
import ma.youcode.managment_tournoi_backend.entity.Match;
import ma.youcode.managment_tournoi_backend.entity.MatchTeam;
import ma.youcode.managment_tournoi_backend.entity.Team;
import ma.youcode.managment_tournoi_backend.entity.enums.LevelEnum;
import ma.youcode.managment_tournoi_backend.repository.MatchTeamRepository;
import ma.youcode.managment_tournoi_backend.service.MatchService;
import ma.youcode.managment_tournoi_backend.service.MatchTeamService;
import ma.youcode.managment_tournoi_backend.service.TeamService;
import ma.youcode.managment_tournoi_backend.util.matchTeam.MatchTeamUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public List<MatchTeam> createMatchTeam(Match match, List<UUID> teamId, UUID arbitraire, LevelEnum levelEnum) {
        MatchTeamUtils.validateSizeTeamPlayingInMatch(teamId.size());
        List<Team> teamList = MatchTeamUtils.getTeamList(teamId);
        Match matchSaved = matchService.createMatch(match, arbitraire);
        List<MatchTeam> matchTeamList = MatchTeamUtils.createMatchTeamList(teamList, matchSaved, levelEnum);
        return matchTeamRepository.saveAll(matchTeamList);
    }


    @Override
    public Page<MatchTeam> getAllMatchTeam(Pageable pageable) {
        return matchTeamRepository.findAll(pageable);
    }
    @Override
    public List<MatchTeam> updateMatchTeam(Match match, List<UUID> teamId, UUID arbitraire, LevelEnum levelEnum) {
        matchService.getMatchById(match.getCode_match());
        MatchTeamUtils.validateSizeTeamPlayingInMatch(teamId.size());
        List<Team> teamList = MatchTeamUtils.getTeamList(teamId);
        matchTeamRepository.deleteAllByMatch_matchId(match.getCode_match());
        Match matchUpdated= matchService.updateMatch(match, arbitraire);
        List<MatchTeam> matchTeamList = MatchTeamUtils.createMatchTeamList(teamList, matchUpdated, levelEnum);
        return matchTeamRepository.saveAll(matchTeamList);
    }
    @Override
    public List<MatchTeam> getMatchTeamByTeamId(UUID teamId) {
        return matchTeamRepository.findByMatch_teamId(teamId);
    }
    public List<MatchTeam> getMatchTeamByMatchId(UUID matchId) {
        return matchTeamRepository.findByMatch_matchId(matchId);
    }
}
