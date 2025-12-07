package com.example.demo.dto.appointment;

import com.example.demo.validation.ExistsIdValid;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AppointmentRequestDto {

    @NotNull(message = "Doctor ID is required")
    @ExistsIdValid(entity = com.example.demo.model.Doctor.class, message = "Doctor does not exist")
    private Long doctorId;

    @NotNull(message = "Customer ID is required")
    @ExistsIdValid(entity = com.example.demo.model.Customer.class, message = "Customer does not exist")
    private Long customerId;

    @NotNull(message = "Room ID is required")
    @ExistsIdValid(entity = com.example.demo.model.Room.class, message = "Room does not exist")
    private Long roomId;

    @NotNull(message = "Specialization ID is required")
    @ExistsIdValid(entity = com.example.demo.model.Specialization.class, message = "Specialization does not exist")
    private Long specializationId;

    @NotNull(message = "Appointment date is required")
    @FutureOrPresent(message = "Appointment date must be today or in the future")
    private LocalDate date;

    @NotNull(message = "Start time is required")
    private LocalTime startTime;
}
