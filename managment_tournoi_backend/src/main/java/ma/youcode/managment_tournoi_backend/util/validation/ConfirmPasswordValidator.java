package ma.youcode.managment_tournoi_backend.util.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.reflect.Field;

public class ConfirmPasswordValidator implements ConstraintValidator<ConfirmPassword, Object> {
    public String field;
    public String confirmPassword;

    @Override
    public void initialize(ConfirmPassword constraintAnnotation) {
        field = constraintAnnotation.field();
        confirmPassword = constraintAnnotation.confirmationField();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        try{
            Field declaredField = o.getClass().getDeclaredField(this.field);
            declaredField.setAccessible(true);
            Object fieldValue =  declaredField.get(o);
            Field confirmField = o.getClass().getDeclaredField(this.confirmPassword);
            declaredField.setAccessible(true);
            Object confirmFieldValue =  declaredField.get(o);
            if(field!=null && !confirmFieldValue.equals(confirmField)){
                throw new RuntimeException("Confirm password does not match");
            }

        }catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return true;
    }
}
