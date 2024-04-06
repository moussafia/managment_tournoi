package ma.youcode.managment_tournoi_backend.util.response;

import java.util.HashMap;
import java.util.List;

public class XlsxFieldErrorResponse {
    private final String message;
    private final HashMap<Integer, List<String>> validationFieldMaps;

    public XlsxFieldErrorResponse(String message, HashMap<Integer, List<String>> validationFieldMaps) {
        this.message = message;
        this.validationFieldMaps = validationFieldMaps;
    }

    public String getMessage() {
        return message;
    }

    public HashMap<Integer, List<String>> getValidationFieldMaps() {
         return validationFieldMaps;
    }
}
