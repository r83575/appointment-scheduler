package com.example.demo.validation;

import com.example.demo.dto.customer.CustomerRequestDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PhoneNumbersValidator implements ConstraintValidator<PhoneNumbersValid, CustomerRequestDto> {

    private final String[] allowedPrefixes = {
            "02", "03", "04", "08", "09",
            "050", "051", "052", "053", "054", "055", "056", "058", "059",
            "072", "073", "074", "076", "077", "079"
    };

    @Override
    public boolean isValid(CustomerRequestDto dto, ConstraintValidatorContext context) {
        String phone1 = dto.getPhoneNumber();
        String phone2 = dto.getAnotherPhoneNumber();

        if (phone2 != null && !phone2.isEmpty() &&
                (phone1 == null || phone1.isEmpty())) {
            return false;
        }

        if (phone1 == null || !phone1.matches("\\d{9}|\\d{10}")) return false;

        if (phone2 != null && !phone2.isEmpty()) {
            if (!phone2.matches("\\d{9}|\\d{10}")) return false;
        }

        if (!hasValidPrefix(phone1)) return false;
        if (phone2 != null && !phone2.isEmpty() && !hasValidPrefix(phone2)) return false;

        if (phone2 != null && !phone2.isEmpty() && phone1.equals(phone2)) return false;

        return true;
    }

    private boolean hasValidPrefix(String phone) {
        for (String prefix : allowedPrefixes) {
            if (phone.startsWith(prefix)) return true;
        }
        return false;
    }
}
