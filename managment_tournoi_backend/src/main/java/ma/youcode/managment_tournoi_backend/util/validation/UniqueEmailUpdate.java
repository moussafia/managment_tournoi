package ma.youcode.managment_tournoi_backend.util.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueEmailUpdateValidator.class)
public @interface UniqueEmailUpdate {
    String message() default "email already exists";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String email();
    String userId();
}
