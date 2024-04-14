package ma.youcode.managment_tournoi_backend.rest.controller;

import lombok.RequiredArgsConstructor;
import ma.youcode.managment_tournoi_backend.dto.participantDto.ParticipantShowDto;
import ma.youcode.managment_tournoi_backend.dto.teamDto.TeamShowDto;
import ma.youcode.managment_tournoi_backend.entity.Team;
import ma.youcode.managment_tournoi_backend.mapper.ParticipantMapper;
import ma.youcode.managment_tournoi_backend.mapper.TeamMapper;
import ma.youcode.managment_tournoi_backend.service.TeamService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/team/edit")
@RequiredArgsConstructor
public class TeamController {
    private final TeamService teamService;
    @GetMapping
    public ResponseEntity<Page<TeamShowDto>> getAllTeams(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Team> teams = teamService.getAllTeams(pageable);
        Page<TeamShowDto> membersDtos =  teams.map(TeamMapper.INSTANCE::TeamToTeamShowDto);
        return ResponseEntity.ok(membersDtos);
    }
    @GetMapping("/{id}")
    public ResponseEntity<TeamShowDto> getTeamById(@PathVariable("id") UUID id) {
        Team team = teamService.getTeamById(id);
        List<ParticipantShowDto> participantShowDtos = team.getParticipants().stream().map(ParticipantMapper.INSTANCE::toParticipantShowDto).toList();
        return ResponseEntity.ok(TeamShowDto.toTeamShowDto(team.getParticipants(), participantShowDtos));
    }
}
