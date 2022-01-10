package ir.maktab.homeservicesystem.exception;

public class NotFoundObjectException extends RuntimeException {
    public NotFoundObjectException() {
    }

    public NotFoundObjectException(String message) {
        super(message);
    }
}
