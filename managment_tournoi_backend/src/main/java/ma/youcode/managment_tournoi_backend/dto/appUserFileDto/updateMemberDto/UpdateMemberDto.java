package ma.youcode.managment_tournoi_backend.dto.appUserFileDto.updateMemberDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import ma.youcode.managment_tournoi_backend.util.validation.UniqueEmail;

import java.util.UUID;

public class UpdateMemberDto {
    @NotNull(message = "id shouldn't be null")
    @NotBlank(message = "id shouldn't be blank")
    private UUID id;
    @NotNull(message = "first name shouldn't be null")
    @NotBlank(message = "first name shouldn't be blank")
    @Pattern(regexp = "[a-zA-Z]+", message = "first name should contains only character")
    private String firstName;
    @NotNull(message = "last name shouldn't be null")
    @NotBlank(message = "last name shouldn't be blank")
    @Pattern(regexp = "[a-zA-Z]+", message = "last name should contains only character")
    private String lastName;
    @UniqueEmail
    @NotNull(message = "email shouldn't be null")
    @NotBlank(message = "email shouldn't be blank")
    @Pattern(regexp="^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3})+$",message = "email should be like email@email.xx")
    private String email;
    @NotNull(message = "class name shouldn't be null")
    @NotBlank(message = "class name shouldn't be null")
    private String className;
    @NotNull(message = "first name shouldn't be blank")
    @NotBlank(message = "first name shouldn't be blank")
    @Pattern(regexp = "^https://intranet.youcode.ma/storage/users/profile/[^/]+\\.JPG$",
            message = "Pattern of URL is not correct")
    private String urlPicture;
}
