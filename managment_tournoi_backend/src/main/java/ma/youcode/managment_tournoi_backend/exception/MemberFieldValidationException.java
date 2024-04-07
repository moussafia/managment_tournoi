package ma.youcode.managment_tournoi_backend.exception;

import lombok.Getter;
import java.util.List;
@Getter
public class MemberFieldValidationException extends RuntimeException {
    private final List<String> fieldErrors;
    public MemberFieldValidationException(String message, List<String> memberFieldsError) {
        super(message);
        this.fieldErrors = memberFieldsError;
    }
}
