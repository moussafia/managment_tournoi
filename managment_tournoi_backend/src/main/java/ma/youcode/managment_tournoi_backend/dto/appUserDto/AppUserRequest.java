package ma.youcode.managment_tournoi_backend.dto.appUserDto;

import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppUserRequest {
    private String First_name;
    private String Last_name;
    private String Email;
    private String Class_name;
    private String Url_Picture;
}
