package com.capgemini.Exceptions;

public class IncorrectObjectException extends RuntimeException {
    public IncorrectObjectException() {
        super();
    }

    public IncorrectObjectException(String message) {
        super(message);
    }
}
