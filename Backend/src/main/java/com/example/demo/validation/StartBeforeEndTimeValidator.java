package com.example.demo.validation;

import com.example.demo.dto.availability.DoctorAvailabilityRequestDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StartBeforeEndTimeValidator implements ConstraintValidator<StartBeforeEndTimeValid, DoctorAvailabilityRequestDto> {

    @Override
    public boolean isValid(DoctorAvailabilityRequestDto dto, ConstraintValidatorContext context) {
        if (dto.getStartTime() == null || dto.getEndTime() == null) {
            return true;
        }

        return dto.getStartTime().isBefore(dto.getEndTime());
    }
}
