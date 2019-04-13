package ro.ccpatrut.authorizationserver.services.exception;

public class AlreadyExistingUserException extends RuntimeException {

    public AlreadyExistingUserException(final String errorMessage) {
        super(errorMessage);
    }
}
