package com.example.demo.dto.availability;

import com.example.demo.validation.StartBeforeEndTimeValid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@StartBeforeEndTimeValid
public class DoctorAvailabilityRequestDto {

    @NotNull(message = "Date is required")
    @FutureOrPresent(message = "Date must be today or in the future")
    private LocalDate date;

    @NotNull(message = "Start time is required")
    private LocalTime startTime;

    @NotNull(message = "End time is required")
    private LocalTime endTime;

    @NotNull(message = "Doctor ID is required")
    private Long doctorId;
}
