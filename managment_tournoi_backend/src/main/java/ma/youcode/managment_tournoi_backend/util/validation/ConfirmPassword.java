package ma.youcode.managment_tournoi_backend.util.validation;

import com.nimbusds.jose.Payload;
import jakarta.validation.Constraint;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ConfirmPasswordValidator.class)
@Documented
public @interface ConfirmPassword {
    String message() default "Password does not match";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String field();
    String confirmationField();
    @Target({ElementType.TYPE})

    @Retention(RetentionPolicy.RUNTIME)

    @interface List {

        ConfirmPassword[] value();

    }
}
