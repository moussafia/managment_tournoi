package ma.youcode.managment_tournoi_backend.rest.handler;

import ma.youcode.managment_tournoi_backend.exception.XLSXFieldException;
import ma.youcode.managment_tournoi_backend.util.response.XlsxFieldErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(value = XLSXFieldException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<XlsxFieldErrorResponse> handleXlsxFieldError(XLSXFieldException xlsxFieldException) {
        final XlsxFieldErrorResponse xlsxFieldErrorResponse = new XlsxFieldErrorResponse(xlsxFieldException.getMessage(), xlsxFieldException.getValidationFieldMaps());
        return new ResponseEntity<>(xlsxFieldErrorResponse, HttpStatus.BAD_REQUEST);
    }
}
