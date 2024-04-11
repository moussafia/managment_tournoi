package ma.youcode.managment_tournoi_backend.dto.teamDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TeamCreateDto {
    @NotNull(message = "name of team is required")
    @NotBlank(message = "name of team should not be blank")
    private String nameTeam;
}
