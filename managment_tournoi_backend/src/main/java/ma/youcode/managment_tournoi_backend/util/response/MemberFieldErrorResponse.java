package ma.youcode.managment_tournoi_backend.util.response;

import java.util.HashMap;
import java.util.List;

public class MemberFieldErrorResponse {
    private final String message;
    private final List<String> validationFieldList;

    public MemberFieldErrorResponse(String message, List<String> validationFieldMaps) {
        this.message = message;
        this.validationFieldList = validationFieldMaps;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getValidationFieldMaps() {
        return validationFieldList;
    }
}
