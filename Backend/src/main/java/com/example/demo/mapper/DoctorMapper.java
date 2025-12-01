package com.example.demo.mapper;

import com.example.demo.dto.doctor.*;
import com.example.demo.model.Doctor;

public class DoctorMapper {

    public static Doctor toEntity(DoctorRequestDto dto) {
        return Doctor.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .build();
    }

    public static DoctorResponseDto toDto(Doctor entity) {
        DoctorResponseDto dto = new DoctorResponseDto();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        return dto;
    }
}
