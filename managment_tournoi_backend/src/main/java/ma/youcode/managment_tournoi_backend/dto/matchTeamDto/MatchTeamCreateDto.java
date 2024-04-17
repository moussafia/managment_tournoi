package ma.youcode.managment_tournoi_backend.dto.matchTeamDto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.youcode.managment_tournoi_backend.dto.matchDto.MatchCreateDto;

import java.util.List;
import java.util.UUID;
@Data
@AllArgsConstructor @NoArgsConstructor
public class MatchTeamCreateDto {
    MatchCreateDto match;
    List<UUID> teamId;
    UUID arbitraire;
    String levelEnum;
}
