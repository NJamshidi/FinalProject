package ir.maktab.homeservicesystem.exception;

public class NotFoundObjectException extends RuntimeException {

    public NotFoundObjectException(String message, int id) {
        super(message);
    }


}
