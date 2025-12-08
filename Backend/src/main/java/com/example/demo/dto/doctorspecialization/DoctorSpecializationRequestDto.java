package com.example.demo.dto.doctorspecialization;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import com.example.demo.validation.ExistsIdValid;

@Data
public class DoctorSpecializationRequestDto {

    @NotNull(message = "doctorId is required")
    @ExistsIdValid(entity = com.example.demo.model.Doctor.class, message = "Doctor does not exist")
    private Long doctorId;

    @NotNull(message = "specializationId is required")
    @ExistsIdValid(entity = com.example.demo.model.Specialization.class, message = "Specialization does not exist")
    private Long specializationId;
}
