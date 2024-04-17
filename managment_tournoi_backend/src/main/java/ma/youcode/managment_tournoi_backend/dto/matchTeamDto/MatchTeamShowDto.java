package ma.youcode.managment_tournoi_backend.dto.matchTeamDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.youcode.managment_tournoi_backend.dto.teamDto.TeamShowDto;
import ma.youcode.managment_tournoi_backend.entity.embedded.MatchTeamEmbeddedId;
import ma.youcode.managment_tournoi_backend.entity.enums.LevelEnum;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MatchTeamShowDto {
    private MatchTeamEmbeddedId id;
    private TeamShowDto team;
    private boolean passed;
    private boolean win;
    private boolean draw;
    private LevelEnum level;
    private int result;
}
