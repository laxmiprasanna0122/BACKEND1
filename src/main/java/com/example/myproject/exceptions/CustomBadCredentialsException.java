package com.example.myproject.exceptions;

public class CustomBadCredentialsException extends RuntimeException {

    public CustomBadCredentialsException() {
    }

    public CustomBadCredentialsException(String message) {
        super(message);
    }

    public CustomBadCredentialsException(String message, Throwable cause) {
        super(message, cause);
    }
}
