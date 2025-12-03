package com.example.demo.dto.doctorspecialization;

import lombok.Data;

@Data
public class DoctorSpecializationRequestDto {
    private Long doctorId;
    private Long specializationId;
}
