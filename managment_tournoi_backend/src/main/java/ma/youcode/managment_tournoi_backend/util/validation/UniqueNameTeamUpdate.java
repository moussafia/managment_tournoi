package ma.youcode.managment_tournoi_backend.util.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueNameTeamUpdateValidator.class)
public @interface UniqueNameTeamUpdate {
    String message() default "Name of team already exists";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String nameOfTeam();
    String userId();
}
