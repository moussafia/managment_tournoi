package ma.youcode.managment_tournoi_backend.security.service.impl;

import lombok.RequiredArgsConstructor;
import ma.youcode.managment_tournoi_backend.entity.AppRole;
import ma.youcode.managment_tournoi_backend.entity.AppUser;
import ma.youcode.managment_tournoi_backend.entity.RefreshToken;
import ma.youcode.managment_tournoi_backend.exception.EntityNotFoundException;
import ma.youcode.managment_tournoi_backend.repository.AppUserRepository;
import ma.youcode.managment_tournoi_backend.security.authDto.AccessTokenRequestDto;
import ma.youcode.managment_tournoi_backend.security.exception.TokenException;
import ma.youcode.managment_tournoi_backend.security.repository.RefreshTokenRepository;
import ma.youcode.managment_tournoi_backend.security.service.IJWTService;
import ma.youcode.managment_tournoi_backend.security.service.IRefreshTokenService;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenService implements IRefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final AppUserRepository userRepository;
    private final IJWTService jwtService;

    @Value("${token.refresh.date-expiration-minute}")
    private long dateExpirationRefreshToken;

    @Override
    public Map<String, String> generateAccessAndRefreshToken(Authentication authentication,
                                                             AppRole roles) {
        String subject = authentication.getName();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Instant instant = Instant.now();
        Map<String, String> token = new HashMap<>();
        token.put("access_Token", jwtService.jwtAccessTokenEncoded(subject, instant, authorities, roles));
        token.put("refresh_Token", this.jwtRefreshTokenEncoded(subject, instant));
        return token;
    }

    @Override
    public String jwtRefreshTokenEncoded(String subject, Instant instant){

        AppUser appUser =userRepository.findByEmailIgnoreCaseOrUsernameIgnoreCase(subject, subject)
                .orElseThrow(() ->  new EntityNotFoundException("resources not found"));
        OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.UTC);
        OffsetDateTime plusOneYear = offsetDateTime.plusMonths(dateExpirationRefreshToken);
        Instant result = plusOneYear.toInstant();
        RefreshToken refreshToken = new RefreshToken()
                .builder()
                .refreshToken(Base64.getEncoder().encodeToString(UUID.randomUUID().toString().getBytes()))
                .expiryDate(result)
                .revoked(false)
                .user(appUser)
                .build();
        RefreshToken refreshTokenSaved = this.refreshTokenRepository.save(refreshToken);
        return refreshTokenSaved.getRefreshToken();

    }

    @Override
    public Map<String,String> generateAccessTokenByRefreshToken(String refreshToken){
        AppUser user = refreshTokenRepository.findByRefreshToken(refreshToken)
                .map(this::verifyExpiration)
                .map(this::verifyIsRevoked)
                .orElseThrow(()-> new RuntimeException("refresh token not found"))
                .getUser();
        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        String accessToken = jwtService.jwtAccessTokenEncoded(user.getEmail(), Instant.now(),
                authentication.getAuthorities(),user.getRole());
        Map<String, String> token = new HashMap<>();
        token.put("access_Token", accessToken);
        return token;
    }

    @Override
    public RefreshToken verifyExpiration(RefreshToken refreshToken){
        if(refreshToken == null){
            throw new TokenException(null, "Token is null");
        }
        if(refreshToken.getExpiryDate().compareTo(Instant.now()) < 0){
            throw new TokenException(refreshToken.getRefreshToken(), "Refresh token was expired. Please make a new authentication request");
        }
        return refreshToken;
    }

    @Override
    public RefreshToken verifyIsRevoked(RefreshToken refreshToken){
        if(refreshToken.isRevoked()){
            throw new TokenException(refreshToken.getRefreshToken(), "Refresh token was expired. Please make a new authentication request");
        }
        return refreshToken;
    }


    @Override
    public void revokeRefreshToken(AccessTokenRequestDto refreshTokenDto){
        RefreshToken refreshToken = refreshTokenRepository.findByRefreshToken(refreshTokenDto.refreshToken())
                .orElseThrow(()-> new RuntimeException("refresh token not found"));
        refreshToken.setRevoked(true);
        refreshTokenRepository.save(refreshToken);
    }
}
