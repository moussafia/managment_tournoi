package ma.youcode.managment_tournoi_backend.dto.appUserFileDto.updateMemberDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import ma.youcode.managment_tournoi_backend.util.validation.ConfirmPassword;

import java.util.UUID;
@Data
@Builder
public class PasswordRequestUpdateDto {
    @NotNull(message = "id shouldn't be null")
    @NotBlank(message = "id shouldn't be blank")
    private UUID memberId;
    @NotNull(message = "password shouldn't be null")
    @NotBlank(message = "password shouldn't be blank")
    private String oldPassword;
    @NotNull(message = "confirm password shouldn't be null")
    @NotBlank(message = "confirm password shouldn't be blank")
    @ConfirmPassword(field = "newPassword", confirmationField = "ConfirmNewPassword")
    private String newPassword;
    @NotNull(message = "confirm password shouldn't be null")
    @NotBlank(message = "confirm password shouldn't be blank")
    private String ConfirmNewPassword;
}
