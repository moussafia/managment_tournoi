package ma.youcode.managment_tournoi_backend.rest.controller;

import lombok.RequiredArgsConstructor;
import ma.youcode.managment_tournoi_backend.dto.matchDto.MatchShowDto;
import ma.youcode.managment_tournoi_backend.dto.matchTeamDto.MatchTeamCreateDto;
import ma.youcode.managment_tournoi_backend.entity.Match;
import ma.youcode.managment_tournoi_backend.entity.MatchTeam;
import ma.youcode.managment_tournoi_backend.entity.enums.LevelEnum;
import ma.youcode.managment_tournoi_backend.mapper.MatchMapper;
import ma.youcode.managment_tournoi_backend.service.MatchTeamService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/matchTeam")
@RequiredArgsConstructor
public class MatchTeamController {
    private final MatchTeamService matchTeamService;
    @PostMapping
    public ResponseEntity<MatchShowDto> createMatchTeam(@RequestBody MatchTeamCreateDto matchTeamCreateDto) {
        Match match = MatchMapper.INSTANCE.MatchCreateDtoToMatch(matchTeamCreateDto.getMatch());
        LevelEnum levelEnum = LevelEnum.valueOf(matchTeamCreateDto.getLevelEnum());
        List<MatchTeam> matchTeam = matchTeamService.createMatchTeam(match, matchTeamCreateDto.getTeamId(), matchTeamCreateDto.getArbitraire(), levelEnum);
        MatchShowDto matchMapped = MatchMapper.INSTANCE.MatchToMatchShowDto(matchTeam.get(0).getMatch());
        return ResponseEntity.ok(matchMapped);
    }

}
