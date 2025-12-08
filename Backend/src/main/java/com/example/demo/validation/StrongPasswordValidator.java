package com.example.demo.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StrongPasswordValidator implements ConstraintValidator<StrongPasswordValid, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return false;

        boolean length = value.length() >= 8;
        boolean upper = value.matches(".*[A-Z].*");
        boolean lower = value.matches(".*[a-z].*");
        boolean digit = value.matches(".*\\d.*");
        boolean special = value.matches(".*[@$!%*?&].*");

        return length && upper && lower && digit && special;
    }
}

