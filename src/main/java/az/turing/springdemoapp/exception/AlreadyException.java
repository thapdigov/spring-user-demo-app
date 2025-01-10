package az.turing.springdemoapp.exception;

public class AlreadyException extends RuntimeException{
    public AlreadyException(String message) {
        super(message);
    }
}
