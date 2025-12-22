package com.example.demo.exception;

public abstract class BaseApplicationException extends RuntimeException {

    public BaseApplicationException(String message) {
        super(message);
    }
}
