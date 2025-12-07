package com.example.demo.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IsraeliIdValidator.class)
public @interface ValidIsraeliId {
    String message() default "invalid Israeli ID number";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
