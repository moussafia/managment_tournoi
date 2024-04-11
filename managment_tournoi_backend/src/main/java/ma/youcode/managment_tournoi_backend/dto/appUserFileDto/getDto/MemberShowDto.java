package ma.youcode.managment_tournoi_backend.dto.appUserFileDto.getDto;

import lombok.Builder;
import lombok.Data;
import ma.youcode.managment_tournoi_backend.entity.AppRole;

import java.time.LocalDateTime;
import java.util.UUID;
@Data
@Builder
public class MemberShowDto {
    private UUID id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String urlPicture;
    private String className;
    private LocalDateTime createdAt;
    private AppRole role;
}
