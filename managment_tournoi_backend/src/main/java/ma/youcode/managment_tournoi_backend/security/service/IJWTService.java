package ma.youcode.managment_tournoi_backend.security.service;


import ma.youcode.managment_tournoi_backend.entity.AppRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.Collection;


public interface IJWTService {
    String jwtAccessTokenEncoded(String subject, Instant instant,
                                        Collection<? extends GrantedAuthority> authorities,
                                        AppRole roles);

    String extractUserName(String token);
    boolean isTokenValid(String jwt, UserDetails userDetails);
}
