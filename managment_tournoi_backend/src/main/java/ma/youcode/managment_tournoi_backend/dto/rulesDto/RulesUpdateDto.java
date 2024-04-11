package ma.youcode.managment_tournoi_backend.dto.rulesDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RulesUpdateDto {
    @NotNull(message = "id of rule is required")
    @NotBlank(message = "id should not be blank")
    private Long id;
    @NotNull(message = "description of rules is required")
    @NotBlank(message = "rule should not be blank")
    private String description;
}
