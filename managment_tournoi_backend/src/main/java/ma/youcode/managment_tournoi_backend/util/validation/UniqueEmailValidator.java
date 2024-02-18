package ma.youcode.managment_tournoi_backend.util.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
import ma.youcode.managment_tournoi_backend.repository.AppUserRepository;

@AllArgsConstructor

public class UniqueEmailValidator  implements ConstraintValidator<UniqueEmail, String> {
    private AppUserRepository userRepository;
    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return email !=null
                &&
                userRepository.findByEmail(email).orElse(null) == null;
    }
}
