package ma.youcode.managment_tournoi_backend.dto.teamDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.youcode.managment_tournoi_backend.dto.participantDto.ParticipantShowDto;

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
}
