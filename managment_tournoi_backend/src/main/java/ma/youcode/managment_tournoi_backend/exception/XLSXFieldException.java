package ma.youcode.managment_tournoi_backend.exception;

import lombok.Getter;

import java.util.HashMap;
import java.util.List;
@Getter
public class XLSXFieldException extends RuntimeException {
    private final HashMap<Integer, List<String>> validationFieldMaps;
    public XLSXFieldException(String message, HashMap<Integer, List<String>> validationFieldMaps1) {
        super(message);
        this.validationFieldMaps = validationFieldMaps1;
    }
}
