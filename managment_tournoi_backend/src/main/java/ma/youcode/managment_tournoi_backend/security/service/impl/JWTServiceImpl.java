package ma.youcode.managment_tournoi_backend.security.service.impl;

import lombok.RequiredArgsConstructor;
import ma.youcode.managment_tournoi_backend.entity.AppRole;
import ma.youcode.managment_tournoi_backend.security.service.IJWTService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collection;


@Service
@RequiredArgsConstructor
public class JWTServiceImpl implements IJWTService {

    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;
    @Value("${token.access.date-expiration-minute}")
    private long dateExpirationAccessToken;


    @Override
    public String jwtAccessTokenEncoded(String subject,
                                        Instant instant,
                                        Collection<? extends GrantedAuthority> authorities,
                                        AppRole appRole){

        String role = appRole.getName().name();
        return jwtEncoder
                .encode(JwtEncoderParameters.from(buildToken(subject, instant, role)))
                .getTokenValue();
    }

    @Override
    public String extractUserName(String token){
        Jwt jwt = jwtDecoder.decode(token);
        return jwt.getSubject();
    }


    @Override
    public boolean isTokenValid(String jwt, UserDetails userDetails) {
        final String username = extractUserName(jwt);
        boolean h = (username.equals(userDetails.getUsername()))
                && !isTokenExpired(jwt);
        return (username.equals(userDetails.getUsername()))
                && !isTokenExpired(jwt);
    }

    private boolean isTokenExpired(String token) {

        Jwt jwt= jwtDecoder.decode(token);
        final Instant dateExpiration = jwt.getExpiresAt();
        return dateExpiration.isBefore(Instant.now());
    }

    private JwtClaimsSet buildToken(String subject,
                                    Instant instant,
                                    String role){

        return JwtClaimsSet.builder()
                .subject(subject)
                .issuedAt(instant)
                .expiresAt(instant.plus(this.dateExpirationAccessToken, ChronoUnit.MINUTES))
                .issuer("Y.soccer-service")
                .claim("scope",role)
                .claim("type_token","ACCESS_TOKEN")
                .build();
    }
}
