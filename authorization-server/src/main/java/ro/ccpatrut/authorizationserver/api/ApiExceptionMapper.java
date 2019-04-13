package ro.ccpatrut.authorizationserver.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ro.ccpatrut.authorizationserver.services.exception.AlreadyExistingUserException;

@RestControllerAdvice
public class ApiExceptionMapper {

    @ExceptionHandler(AlreadyExistingUserException.class)
    public ResponseEntity<String> handleAlreadyExistingUserError() {
        return new ResponseEntity<>("User already exists", HttpStatus.CONFLICT);
    }
}
