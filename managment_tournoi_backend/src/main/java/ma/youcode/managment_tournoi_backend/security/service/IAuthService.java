package ma.youcode.managment_tournoi_backend.security.service;

import ma.youcode.managment_tournoi_backend.entity.AppUser;
import ma.youcode.managment_tournoi_backend.security.authDto.AuthenticationRequestDto;
import ma.youcode.managment_tournoi_backend.security.authDto.AuthenticationResponseDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IAuthService {

    AuthenticationResponseDto authenticate(AuthenticationRequestDto authenticationRequestDto);
    AppUser me();
}
