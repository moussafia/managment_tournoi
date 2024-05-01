package ma.youcode.managment_tournoi_backend.security.authDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthenticationRequestDto(
        @NotBlank(message = "username cannot be blank")
        String username,
        @NotBlank(message = "Email cannot be blank")
        @Size(min = 2, message = "Password must be at least 2 characters long")
        String password
) {
}