package com.ob11to.spring.exception;

public class AuthException extends RuntimeException {

    public AuthException(String message) {
        super(message);
    }
}
