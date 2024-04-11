package ma.youcode.managment_tournoi_backend.dto.rulesDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RulesCreateDto {
    @NotNull(message = "description of rules is required")
    @NotBlank(message = "rule should not be blank")
    private String description;
}
