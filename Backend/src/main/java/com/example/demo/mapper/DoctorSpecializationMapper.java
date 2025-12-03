package com.example.demo.mapper;

import com.example.demo.dto.doctorspecialization.*;
import com.example.demo.model.DoctorSpecialization;

public class DoctorSpecializationMapper {

    public static DoctorSpecialization toEntity(DoctorSpecializationRequestDto dto) {
        return DoctorSpecialization.builder()
                .doctorId(dto.getDoctorId())
                .specializationId(dto.getSpecializationId())
                .build();
    }

    public static DoctorSpecializationResponseDto toDto(DoctorSpecialization entity) {
        DoctorSpecializationResponseDto dto = new DoctorSpecializationResponseDto();
        dto.setDoctorId(entity.getDoctorId());
        dto.setSpecializationId(entity.getSpecializationId());
        return dto;
    }

    public static void updateEntity(DoctorSpecialization entity, DoctorSpecializationRequestDto dto) {
        entity.setDoctorId(dto.getDoctorId());
        entity.setSpecializationId(dto.getSpecializationId());
    }
}
