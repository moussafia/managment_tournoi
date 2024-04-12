package ma.youcode.managment_tournoi_backend.dto.teamDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;
@Data
public class TeamUpdateDto {
    @NotNull(message = "id of team is required")
    private UUID id;
    @NotNull(message = "name of team is required")
    @NotBlank(message = "name of team should not be blank")
    private String nameTeam;
}
