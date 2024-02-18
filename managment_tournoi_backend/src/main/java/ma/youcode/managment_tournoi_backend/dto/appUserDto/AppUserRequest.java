package ma.youcode.managment_tournoi_backend.dto.appUserDto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import ma.youcode.managment_tournoi_backend.util.validation.UniqueEmail;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppUserRequest {
    @NotNull(message = "first name shouldn't be null")
    @NotBlank(message = "first name shouldn't be blank")
    @Pattern(regexp = "[a-zA-Z]+", message = "first name should contains only character")
    private String FirstName;
    @NotNull(message = "last name shouldn't be null")
    @NotBlank(message = "last name shouldn't be blank")
    @Pattern(regexp = "[a-zA-Z]+", message = "last name should contains only character")
    private String LastName;
    @UniqueEmail
    @NotNull(message = "email shouldn't be null")
    @NotBlank(message = "email shouldn't be blank")
    @Pattern(regexp="^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3})+$",message = "email should be like email@email.xx")
    private String Email;
    @NotNull(message = "class name shouldn't be null")
    @NotBlank(message = "class name shouldn't be null")
    private String ClassName;
    @NotNull(message = "first name shouldn't be blank")
    @NotBlank(message = "first name shouldn't be blank")
    @Pattern(regexp = "^https://intranet.youcode.ma/storage/users/profile/[^/]+\\.JPG$",
            message = "Pattern of URL is not correct")
    private String UrlPicture;
    @JsonIgnore
    private int indexRow;
}
