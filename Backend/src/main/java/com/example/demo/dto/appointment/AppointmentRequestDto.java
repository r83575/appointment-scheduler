package com.example.demo.dto.appointment;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AppointmentRequestDto {
    private Long doctorId;
    private Long customerId;
    private Long roomId;
    private Long specializationId;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
}
