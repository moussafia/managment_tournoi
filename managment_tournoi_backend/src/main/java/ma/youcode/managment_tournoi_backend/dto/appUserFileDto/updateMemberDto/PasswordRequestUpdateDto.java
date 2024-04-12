package ma.youcode.managment_tournoi_backend.dto.appUserFileDto.updateMemberDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.youcode.managment_tournoi_backend.util.validation.ConfirmPassword;

import java.util.UUID;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ConfirmPassword(field = "newPassword", confirmationField = "confirmNewPassword")
public class PasswordRequestUpdateDto {
    @NotNull(message = "id shouldn't be null")
    private UUID memberId;
    @NotNull(message = "password shouldn't be null")
    @NotBlank(message = "password shouldn't be blank")
    private String oldPassword;
    @NotNull(message = "new password shouldn't be null")
    @NotBlank(message = "new password shouldn't be blank")
    private String newPassword;
    @NotNull(message = "confirm password shouldn't be null")
    @NotBlank(message = "confirm password shouldn't be blank")
    private String confirmNewPassword;
}
