package ma.youcode.managment_tournoi_backend.security.authDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ma.youcode.managment_tournoi_backend.entity.AppRole;

import java.util.UUID;

public record AuthenticationResponseDto(
        UUID id,
        String firstName,
        String LastName,
        String username,
        String email,
        AppRole role,
        @JsonProperty("access_token")
        String accessToken,
        @JsonProperty("refresh_token")
        String refreshToken
){
}
