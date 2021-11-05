package KarolSzymaniak.org.Service;

public class NoSufficientFoundException extends RuntimeException {
    public NoSufficientFoundException(String message) {
        super(message);
    }
}
