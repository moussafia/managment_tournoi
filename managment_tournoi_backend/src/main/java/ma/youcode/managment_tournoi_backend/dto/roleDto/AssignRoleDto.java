package ma.youcode.managment_tournoi_backend.dto.roleDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssignRoleDto {
    @NotNull
    private UUID memberId;
    @NotNull
    @NotBlank
    private String roleName;
}
