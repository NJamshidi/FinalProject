package ir.maktab.homeservicesystem.exception;

public class NotFoundObjectException extends RuntimeException {

    public NotFoundObjectException(String message, int id) {
        super(message);
    }

    public NotFoundObjectException() {
    }

    public NotFoundObjectException(String message) {
        super(message);
    }

    public NotFoundObjectException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundObjectException(Throwable cause) {
        super(cause);
    }

    public NotFoundObjectException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
