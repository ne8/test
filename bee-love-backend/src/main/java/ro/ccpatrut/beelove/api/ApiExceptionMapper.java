package ro.ccpatrut.beelove.api;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionMapper {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAlreadyExistingUserError() {
        return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException() {
        return new ResponseEntity<>("Something went wrong", HttpStatus.BAD_REQUEST);
    }
}
