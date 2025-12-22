package com.example.demo.exception;

public class BadRequestException extends BaseApplicationException {
    public BadRequestException(String message) {
        super(message);
    }
}
