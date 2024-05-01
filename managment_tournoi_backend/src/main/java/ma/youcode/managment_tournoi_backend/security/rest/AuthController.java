package ma.youcode.managment_tournoi_backend.security.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.youcode.managment_tournoi_backend.dto.appUserFileDto.getDto.MemberShowDto;
import ma.youcode.managment_tournoi_backend.entity.AppUser;
import ma.youcode.managment_tournoi_backend.mapper.AppUserMapper;
import ma.youcode.managment_tournoi_backend.security.authDto.AccessTokenRequestDto;
import ma.youcode.managment_tournoi_backend.security.authDto.AuthenticationRequestDto;
import ma.youcode.managment_tournoi_backend.security.authDto.AuthenticationResponseDto;
import ma.youcode.managment_tournoi_backend.security.service.IAuthService;
import ma.youcode.managment_tournoi_backend.security.service.IRefreshTokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final IAuthService authService;
    private final IRefreshTokenService refreshTokenService;


    @PostMapping("/logIn")
    public ResponseEntity<AuthenticationResponseDto> logIn(@RequestBody
                                                           @Valid AuthenticationRequestDto authenticationRequestDto){
        return ResponseEntity.ok(authService.authenticate(authenticationRequestDto));
    }
    @PostMapping("/token")
    public ResponseEntity<Map<String, String>> getAccessTokenByRefreshToken(@RequestBody
                                                                            @Valid AccessTokenRequestDto refreshToken){
        return ResponseEntity.ok(refreshTokenService.generateAccessTokenByRefreshToken(refreshToken.refreshToken()));
    }
    @GetMapping("/me")
    public ResponseEntity<MemberShowDto> me(){
        AppUser user = authService.me();
        return ResponseEntity.ok(AppUserMapper.INSTANCE.AppUserToAppUserDto(user));
    }
}
