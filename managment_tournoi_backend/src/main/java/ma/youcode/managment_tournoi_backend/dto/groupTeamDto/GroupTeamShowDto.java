package ma.youcode.managment_tournoi_backend.dto.groupTeamDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.youcode.managment_tournoi_backend.dto.teamDto.TeamShowDto;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GroupTeamShowDto {
    private Long id;
    private Boolean isPassed;
    private Integer points;
    private Integer wins;
    private Integer draws;
    private Integer losses;
    private Integer rank;
    private TeamShowDto team;
}
