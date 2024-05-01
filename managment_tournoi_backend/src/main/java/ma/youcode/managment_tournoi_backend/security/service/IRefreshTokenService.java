package ma.youcode.managment_tournoi_backend.security.service;

import ma.youcode.managment_tournoi_backend.entity.AppRole;
import ma.youcode.managment_tournoi_backend.entity.RefreshToken;
import org.springframework.security.core.Authentication;

import java.time.Instant;
import java.util.Map;

public interface IRefreshTokenService {

    Map<String, String> generateAccessAndRefreshToken(Authentication authentication,  AppRole roles);
    String jwtRefreshTokenEncoded(String subject, Instant instant);
    Map<String,String> generateAccessTokenByRefreshToken(String refreshToken);
    RefreshToken verifyIsRevoked(RefreshToken refreshToken);
    RefreshToken verifyExpiration(RefreshToken refreshToken);
}
