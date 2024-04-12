package ma.youcode.managment_tournoi_backend.util.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

import java.lang.reflect.Field;

public class ConfirmPasswordValidator implements ConstraintValidator<ConfirmPassword, Object> {
    public String field;
    public String confirmPassword;
    private String message;

    @Override
    public void initialize(ConfirmPassword constraintAnnotation) {
        field = constraintAnnotation.field();
        confirmPassword = constraintAnnotation.confirmationField();
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Object fieldValue = new BeanWrapperImpl(value).getPropertyValue(field);

        Object fieldMatchValue = new BeanWrapperImpl(value).getPropertyValue(confirmPassword);


        boolean isValid = fieldValue != null && fieldValue.equals(fieldMatchValue);


        if (!isValid) {

            context.disableDefaultConstraintViolation();

            context

                    .buildConstraintViolationWithTemplate(message)

                    .addPropertyNode(confirmPassword)

                    .addConstraintViolation();

        }


        return isValid;
    }
}
