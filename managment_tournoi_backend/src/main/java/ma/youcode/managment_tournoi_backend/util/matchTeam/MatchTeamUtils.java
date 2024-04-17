package ma.youcode.managment_tournoi_backend.util.matchTeam;

import ma.youcode.managment_tournoi_backend.entity.Match;
import ma.youcode.managment_tournoi_backend.entity.MatchTeam;
import ma.youcode.managment_tournoi_backend.entity.Team;
import ma.youcode.managment_tournoi_backend.entity.embedded.MatchTeamEmbeddedId;
import ma.youcode.managment_tournoi_backend.entity.enums.LevelEnum;
import ma.youcode.managment_tournoi_backend.repository.MatchTeamRepository;
import ma.youcode.managment_tournoi_backend.service.TeamService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class MatchTeamUtils {
    private static TeamService teamService;
    public MatchTeamUtils(TeamService teamService) {
        this.teamService = teamService;
    }
    public static List<Team> getTeamList(List<UUID> teamId) {
        List<Team> teamList = new ArrayList<>();
        for (UUID uuid : teamId) {
            Team team = teamService.getTeamById(uuid);
            teamList.add(team);
        }
        return teamList;
    }

    public static void validateSizeTeamPlayingInMatch(int size) {
        if(size != 2)
            throw new RuntimeException("Invalid size of team,Should be 2");
    }
    public static List<MatchTeam> createMatchTeamList(List<Team> teamList, Match matchSaved, LevelEnum levelEnum) {
        List<MatchTeam> matchTeam = new ArrayList<>();
        for (Team team : teamList) {
            MatchTeamEmbeddedId matchTeamEmbeddedId = new MatchTeamEmbeddedId(matchSaved.getCodeMatch(), team.getId());
            matchTeam.add(new MatchTeam().builder()
                    .id(matchTeamEmbeddedId)
                    .isDraw(false)
                    .level(levelEnum)
                    .isPassed(false)
                    .team(team)
                    .match(matchSaved)
                    .result(0)
                    .isWin(false).build());
        }
        return matchTeam;
    }

}
