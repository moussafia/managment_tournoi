package ma.youcode.managment_tournoi_backend.dto.teamDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.youcode.managment_tournoi_backend.dto.participantDto.ParticipantShowDto;
import ma.youcode.managment_tournoi_backend.entity.Participant;

import java.util.List;
import java.util.UUID;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeamShowDto {
    private UUID id;
    private String nameTeam;
    private String logo;
    private String logoPublicId;
    private List<ParticipantShowDto> participants;
    public static TeamShowDto toTeamShowDto(List<Participant> participant, List<ParticipantShowDto> participantShowDtos ) {
        return  new TeamShowDto().builder()
                .nameTeam(participant.get(0).getTeam().getNameTeam())
                .id(participant.get(0).getTeam().getId())
                .logo(participant.get(0).getTeam().getLogo())
                .logoPublicId(participant.get(0).getTeam().getLogoPublicId())
                .participants(participantShowDtos)
                .build();
    }
}
