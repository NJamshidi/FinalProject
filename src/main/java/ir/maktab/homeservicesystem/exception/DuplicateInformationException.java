package ir.maktab.homeservicesystem.exception;

public class DuplicateInformationException extends RuntimeException{
    public DuplicateInformationException() {
    }

    public DuplicateInformationException(String message) {
        super(message);
    }
}
