package com.example.demo.exception;

public class ResourceNotFoundException extends BaseApplicationException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}

