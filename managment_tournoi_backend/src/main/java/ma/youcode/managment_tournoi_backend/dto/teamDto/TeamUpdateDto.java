package ma.youcode.managment_tournoi_backend.dto.teamDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import ma.youcode.managment_tournoi_backend.util.validation.UniqueNameTeamUpdate;

import java.util.UUID;
@Data
@UniqueNameTeamUpdate(userId = "id" , nameOfTeam = "nameTeam")
public class TeamUpdateDto {
    @NotNull(message = "id of team is required")
    private UUID id;
    @NotNull(message = "name of team is required")
    @NotBlank(message = "name of team should not be blank")
    private String nameTeam;
    @NotNull(message = "url logo of team is required")
    @NotBlank(message = "url logo of team should not be blank")
    private String logo;
    @NotNull(message = "logo public id of team is required")
    @NotBlank(message = "logo public id of team should not be blank")
    private String logoPublicId;
}
