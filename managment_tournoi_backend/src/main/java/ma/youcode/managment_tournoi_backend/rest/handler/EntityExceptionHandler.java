package ma.youcode.managment_tournoi_backend.rest.handler;

import ma.youcode.managment_tournoi_backend.exception.EntityAlreadyExistException;
import ma.youcode.managment_tournoi_backend.exception.EntityNotFoundException;
import ma.youcode.managment_tournoi_backend.util.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EntityExceptionHandler {
    @ExceptionHandler({EntityAlreadyExistException.class, EntityNotFoundException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleMemberFieldError(Exception exception) {
        String errorMessage;
        if (exception instanceof EntityAlreadyExistException) {
            errorMessage = exception.getMessage();
        } else if (exception instanceof EntityNotFoundException) {
            errorMessage = exception.getMessage();
        } else {
            errorMessage = "An unexpected error occurred";
        }

        final ErrorResponse errorResponse = new ErrorResponse(errorMessage);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
