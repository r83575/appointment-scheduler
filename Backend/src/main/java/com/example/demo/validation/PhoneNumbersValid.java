package com.example.demo.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhoneNumbersValidator.class)
public @interface PhoneNumbersValid {
    String message() default "phone numbers are invalid";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
