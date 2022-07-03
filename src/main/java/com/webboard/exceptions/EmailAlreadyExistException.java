package com.webboard.exceptions;

public class EmailAlreadyExistException extends RuntimeException{
    public EmailAlreadyExistException() {
        super();
    }

    public EmailAlreadyExistException(final Throwable throwable) {
        super(throwable);
    }

    public EmailAlreadyExistException(final String message) {
        super(message);
    }
}
