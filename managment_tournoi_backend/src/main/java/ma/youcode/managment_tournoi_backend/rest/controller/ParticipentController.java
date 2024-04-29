package ma.youcode.managment_tournoi_backend.rest.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
 @RequestMapping("api/v1/team/participent")
@RequiredArgsConstructor
public class ParticipentController {
    private final ParticipentService participentService;
    @PostMapping
    public ResponseEntity<TeamShowDto> createTeam(@ModelAttribute ParticipantCreateDto participantCreateDto) {
        Team team = TeamMapper.INSTANCE.TeamCreateDtoToTeam(participantCreateDto.getTeam());
        List<Participant> participant = participentService.createParticipant(participantCreateDto.getUsersIds(), team, participantCreateDto.getLogo(), participantCreateDto.getNumberOfParticipants());
        List<ParticipantShowDto> participantShowDtos = participant.stream().map(ParticipantMapper.INSTANCE::toParticipantShowDto).toList();
        return ResponseEntity.ok(TeamShowDto.toTeamShowDto(participant, participantShowDtos));
    }
    @PutMapping
    public ResponseEntity<TeamShowDto> updateTeam(@ModelAttribute ParticipantUpdateDto participantUpdateDto) {
        Team team = TeamMapper.INSTANCE.TeamUpdateDtoToTeam(participantUpdateDto.getTeam());
        List<Participant> participant = participentService.updateParticipant(participantUpdateDto.getUsersIds(), team, participantUpdateDto.getLogo(), participantUpdateDto.getNumberOfParticipants());
        List<ParticipantShowDto> participantShowDtos = participant.stream().map(ParticipantMapper.INSTANCE::toParticipantShowDto).toList();
        return ResponseEntity.ok(TeamShowDto.toTeamShowDto(participant, participantShowDtos));
    }
    @DeleteMapping
    public ResponseEntity<Map<String, String>> deleteTeam(@RequestParam("id") UUID id, @RequestParam("publicIdLogo") String publicIdLogo) {
        participentService.deleteParticipant(id, publicIdLogo);
        Map<String, String> response = new HashMap<>();
        response.put("message", "team deleted successfully");
        return ResponseEntity.ok(response);
    }
}
