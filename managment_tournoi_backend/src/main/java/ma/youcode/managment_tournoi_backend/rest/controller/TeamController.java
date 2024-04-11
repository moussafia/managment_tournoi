package ma.youcode.managment_tournoi_backend.rest.controller;

import lombok.RequiredArgsConstructor;
import ma.youcode.managment_tournoi_backend.dto.participantDto.ParticipantCreateDto;
import ma.youcode.managment_tournoi_backend.dto.participantDto.ParticipantShowDto;
import ma.youcode.managment_tournoi_backend.dto.participantDto.ParticipantUpdateDto;
import ma.youcode.managment_tournoi_backend.dto.teamDto.TeamShowDto;
import ma.youcode.managment_tournoi_backend.entity.Participant;
import ma.youcode.managment_tournoi_backend.entity.Team;
import ma.youcode.managment_tournoi_backend.mapper.ParticipantMapper;
import ma.youcode.managment_tournoi_backend.mapper.TeamMapper;
import ma.youcode.managment_tournoi_backend.service.ParticipentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
 @RequestMapping("api/v1/team")
@RequiredArgsConstructor
public class TeamController {
    private final ParticipentService participentService;
    @PostMapping
    public ResponseEntity<TeamShowDto> createTeam(@RequestBody ParticipantCreateDto participantCreateDto) {
        Team team = TeamMapper.INSTANCE.TeamCreateDtoToTeam(participantCreateDto.getTeam());
        List<Participant> participant = participentService.createParticipant(participantCreateDto.getUsersIds(), team, participantCreateDto.getLogo());
        List<ParticipantShowDto> participantShowDtos = participant.stream().map(ParticipantMapper.INSTANCE::toParticipantShowDto).toList();
        return ResponseEntity.ok(
                new TeamShowDto().builder()
                        .nameTeam(participant.get(0).getTeam().getNameTeam())
                        .id(participant.get(0).getTeam().getId())
                        .logo(participant.get(0).getTeam().getLogo())
                        .logoPublicId(participant.get(0).getTeam().getLogoPublicId())
                        .participants(participantShowDtos)
                        .build()
        );
    }
    @PutMapping
    public ResponseEntity<TeamShowDto> updateTeam(@RequestBody ParticipantUpdateDto participantUpdateDto) {
        Team team = TeamMapper.INSTANCE.TeamUpdateDtoToTeam(participantUpdateDto.getTeam());
        List<Participant> participant = participentService.updateParticipant(participantUpdateDto.getUsersIds(), team, participantUpdateDto.getLogo());
        List<ParticipantShowDto> participantShowDtos = participant.stream().map(ParticipantMapper.INSTANCE::toParticipantShowDto).toList();
        return ResponseEntity.ok(
                new TeamShowDto().builder()
                        .nameTeam(participant.get(0).getTeam().getNameTeam())
                        .id(participant.get(0).getTeam().getId())
                        .logo(participant.get(0).getTeam().getLogo())
                        .logoPublicId(participant.get(0).getTeam().getLogoPublicId())
                        .participants(participantShowDtos)
                        .build()
        );
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTeam(@PathVariable UUID id) {
        participentService.deleteParticipant(id);
        return ResponseEntity.ok("team deleted successfully");
    }
}
