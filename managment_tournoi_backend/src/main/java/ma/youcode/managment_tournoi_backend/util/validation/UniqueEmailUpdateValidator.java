package ma.youcode.managment_tournoi_backend.util.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import ma.youcode.managment_tournoi_backend.entity.AppUser;
import ma.youcode.managment_tournoi_backend.repository.AppUserRepository;
import org.springframework.beans.BeanWrapperImpl;

import java.util.UUID;

@RequiredArgsConstructor
public class UniqueEmailUpdateValidator implements ConstraintValidator<UniqueEmailUpdate, Object> {
    private final AppUserRepository userRepository;
    private String email;
    private String userId;
    private String message;

    @Override
    public void initialize(UniqueEmailUpdate constraintAnnotation) {
        userId = constraintAnnotation.userId();
        email = constraintAnnotation.email();
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Object emailO = new BeanWrapperImpl(value).getPropertyValue(email);

        Object userIdO = new BeanWrapperImpl(value).getPropertyValue(userId);
        AppUser appUser;
        boolean isValid = true;
        if(emailO !=null && userIdO!=null){
            appUser = userRepository.findByEmail(emailO.toString()).orElse(null);
            if(appUser != null){
                isValid = UUID.fromString((String) userIdO).equals(appUser.getId());
            }
        }

        if (!isValid) {

            context.disableDefaultConstraintViolation();

            context

                    .buildConstraintViolationWithTemplate(message)

                    .addPropertyNode(email)

                    .addConstraintViolation();

        }


        return isValid;
    }
}