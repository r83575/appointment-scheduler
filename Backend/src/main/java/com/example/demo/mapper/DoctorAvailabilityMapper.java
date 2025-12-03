package com.example.demo.mapper;

import com.example.demo.dto.availability.*;
import com.example.demo.model.Doctor;
import com.example.demo.model.DoctorAvailability;

public class DoctorAvailabilityMapper {

    public static DoctorAvailability toEntity(DoctorAvailabilityRequestDto dto) {
        return DoctorAvailability.builder()
                .date(dto.getDate())
                .startTime(dto.getStartTime())
                .endTime(dto.getEndTime())
                .doctor(Doctor.builder().id(dto.getDoctorId()).build())
                .build();
    }

    public static DoctorAvailabilityResponseDto toDto(DoctorAvailability entity) {
        DoctorAvailabilityResponseDto dto = new DoctorAvailabilityResponseDto();
        dto.setId(entity.getId());
        dto.setDate(entity.getDate());
        dto.setStartTime(entity.getStartTime());
        dto.setEndTime(entity.getEndTime());
        dto.setDoctorId(entity.getDoctor().getId());
        return dto;
    }

    public static void updateEntity(DoctorAvailability entity, DoctorAvailabilityRequestDto dto) {
        entity.setDate(dto.getDate());
        entity.setStartTime(dto.getStartTime());
        entity.setEndTime(dto.getEndTime());
        entity.setDoctor(Doctor.builder().id(dto.getDoctorId()).build());
    }
}
