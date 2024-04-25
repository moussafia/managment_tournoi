package ma.youcode.managment_tournoi_backend.rest.controller;

import lombok.RequiredArgsConstructor;
import ma.youcode.managment_tournoi_backend.dto.matchDto.MatchShowDto;
import ma.youcode.managment_tournoi_backend.entity.Match;
import ma.youcode.managment_tournoi_backend.mapper.MatchMapper;
import ma.youcode.managment_tournoi_backend.service.MatchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/match")
@RequiredArgsConstructor
public class MatchController {
    private final MatchService matchService;

    @GetMapping
    public ResponseEntity<MatchShowDto> getMatchToday() {
        Match match = matchService.getMatchToday();
        MatchShowDto matchMapped = MatchMapper.INSTANCE.MatchToMatchShowDto(match);
        return ResponseEntity.ok(matchMapped);
    }
}