package ma.youcode.managment_tournoi_backend.util.member;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Random;

@Component
public class MemberUtils {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
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

}
