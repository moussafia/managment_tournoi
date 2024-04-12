package ma.youcode.managment_tournoi_backend.util.member;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import ma.youcode.managment_tournoi_backend.entity.AppUser;
import ma.youcode.managment_tournoi_backend.exception.MemberFieldValidationException;
import ma.youcode.managment_tournoi_backend.exception.EntityAlreadyExistException;
import ma.youcode.managment_tournoi_backend.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class MemberUtils {
    private final static String firstNamePattern = "[a-zA-Z]+";
    private final static String lastNamePattern = "[a-zA-Z]+";
    private final static String emailPattern = "^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3})+$";
    private final static String urlPattern = "^https://intranet.youcode.ma/storage/users/profile/[^/]+\\.JPG$";
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static AppUserRepository appUserRepository;

    public MemberUtils(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public static String generatePassword(){
        int lengthPassword = 6;
        SecureRandom  random = new SecureRandom();
        StringBuilder password = new StringBuilder(6);
        for (int i = 0; i < lengthPassword; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            password.append(CHARACTERS.charAt(randomIndex));
        }
        return password.toString();
    }
    public static String generateUsername(String firstName, String lastName){
        Random random = new Random();
        int number = random.nextInt(100);
        return (firstName.charAt(0) + "." + lastName + number).toLowerCase();
    }
    public static void checkUserIfAlreadyExist(String email){
        appUserRepository.findByEmail(email)
                .ifPresent((user) -> new EntityAlreadyExistException("user with email " + email + " already exist"));
    }

    public static List<String> validateMemberFieldNotMatchPattern(AppUser appUser) {
        List<String> fieldsName = new ArrayList<>();
        if(!appUser.getFirstName().matches(firstNamePattern) || appUser.getFirstName().isBlank() || appUser.getFirstName().isEmpty() || appUser.getFirstName() == null){
            fieldsName.add("First Name " + appUser.getFirstName());
        }
        if(!appUser.getLastName().matches(lastNamePattern) || appUser.getLastName().isBlank() || appUser.getLastName().isEmpty() || appUser.getLastName() == null){
            fieldsName.add("Last Name " + appUser.getLastName());
        }
        if(!appUser.getUrlPicture().matches(urlPattern) || appUser.getUrlPicture().isBlank() || appUser.getUrlPicture().isEmpty() || appUser.getUrlPicture() == null){
            fieldsName.add("picture url " + appUser.getUrlPicture());
        }
        if(!appUser.getEmail().matches(emailPattern) || appUser.getEmail().isBlank() || appUser.getEmail().isEmpty() || appUser.getEmail() == null){
            fieldsName.add("Email");
        }
        if(appUser.getClassName().isBlank() || appUser.getClassName().isEmpty() || appUser.getClassName() == null){
            fieldsName.add("Class name " + appUser.getClassName());
        }
        return fieldsName;
    }

    public static void validateMemberFieldPattern(AppUser member) {
        List<String> memberFields = validateMemberFieldNotMatchPattern(member);
        if(!memberFields.isEmpty()){
            throw new MemberFieldValidationException("These field(s) is/are not valid. Please correct it/them.", memberFields);
        }
    }
}
