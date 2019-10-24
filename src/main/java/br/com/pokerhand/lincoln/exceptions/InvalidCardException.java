package br.com.pokerhand.lincoln.exceptions;

public class InvalidCardException extends Exception {

    public InvalidCardException() {
        super();
    }

    public InvalidCardException(String message) {
        super(message);
    }

    public InvalidCardException(Throwable throwable) {
        super(throwable);
    }

    public InvalidCardException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
