package ma.youcode.managment_tournoi_backend.security.service.impl;
import lombok.RequiredArgsConstructor;
import ma.youcode.managment_tournoi_backend.entity.AppUser;
import ma.youcode.managment_tournoi_backend.repository.AppUserRepository;
import ma.youcode.managment_tournoi_backend.security.authDto.AuthenticationRequestDto;
import ma.youcode.managment_tournoi_backend.security.authDto.AuthenticationResponseDto;
import ma.youcode.managment_tournoi_backend.security.service.IAuthService;
import ma.youcode.managment_tournoi_backend.security.utiles.SecurityUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {

    private final AppUserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;


    @Override
    public AuthenticationResponseDto authenticate(AuthenticationRequestDto authenticationRequestDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequestDto.username(), authenticationRequestDto.password())
        );
        var user = userRepository
                .findByEmailIgnoreCaseOrUsernameIgnoreCase(authenticationRequestDto.username(), authenticationRequestDto.username())
                .get();
        return generateAccessToken(authentication, user);
    }

    @Override
    public AppUser me() {
        String username = SecurityUtil.getCurrentUsername();
        if(username == null)
            throw new BadCredentialsException("user not found");
        return userRepository
                .findByEmailIgnoreCaseOrUsernameIgnoreCase(username, username)
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }

    private AuthenticationResponseDto generateAccessToken(Authentication authentication, AppUser user){
        Map<String, String> token = refreshTokenService.generateAccessAndRefreshToken(authentication,
                user.getRole());
        return  new AuthenticationResponseDto(
                user.getId(), user.getFirstName(), user.getLastName(), user.getUsername(),
                user.getEmail(), user.getRole(),
                token.get("access_Token"), token.get("refresh_Token")
        );
    }

}
