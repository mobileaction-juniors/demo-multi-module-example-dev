package co.mobileaction.example.web.advice;
import co.mobileaction.example.web.exception.DateOutOfRangeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler
{
    @ExceptionHandler(DateOutOfRangeException.class)
    public ResponseEntity<String> handleDateOutOfRangeException(DateOutOfRangeException exception) {
        String errorMessage = exception.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}
