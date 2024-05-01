package ma.youcode.managment_tournoi_backend.security.authDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AccessTokenRequestDto(
        @NotBlank
        @NotNull(message = "refresh token is valid")
        String refreshToken){
}