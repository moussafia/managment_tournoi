package ma.youcode.managment_tournoi_backend.rest.handler;

import ma.youcode.managment_tournoi_backend.exception.EntityNotFoundException;
import ma.youcode.managment_tournoi_backend.exception.MemberFieldValidationException;
import ma.youcode.managment_tournoi_backend.exception.EntityAlreadyExistException;
import ma.youcode.managment_tournoi_backend.exception.XLSXFieldException;
import ma.youcode.managment_tournoi_backend.util.response.ErrorResponse;
import ma.youcode.managment_tournoi_backend.util.response.MemberFieldErrorResponse;
import ma.youcode.managment_tournoi_backend.util.response.XlsxFieldErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(value = XLSXFieldException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<XlsxFieldErrorResponse> handleXlsxFieldError(XLSXFieldException xlsxFieldException) {
        final XlsxFieldErrorResponse xlsxFieldErrorResponse = new XlsxFieldErrorResponse(xlsxFieldException.getMessage(), xlsxFieldException.getValidationFieldMaps());
        return new ResponseEntity<>(xlsxFieldErrorResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = MemberFieldValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<MemberFieldErrorResponse> handleMemberFieldError(MemberFieldValidationException exception) {
        final MemberFieldErrorResponse memberFieldErrorResponse = new MemberFieldErrorResponse(exception.getMessage(), exception.getFieldErrors());
        return new ResponseEntity<>(memberFieldErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errorMessage = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorMessage.put(error.getField(), error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errorMessage);
    }

}
