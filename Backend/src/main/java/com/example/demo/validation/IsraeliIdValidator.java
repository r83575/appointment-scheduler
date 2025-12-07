package com.example.demo.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IsraeliIdValidator implements ConstraintValidator<IsraeliIdValid, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || !value.matches("\\d{9}")) return false;

        int sum = 0;
        for (int i = 0; i < 9; i++) {
            int digit = Character.getNumericValue(value.charAt(i));
            int calc = digit * ((i % 2) + 1);
            sum += calc > 9 ? calc - 9 : calc;
        }

        return sum % 10 == 0;
    }
}
