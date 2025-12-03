package com.example.demo.mapper;

import com.example.demo.dto.specialization.*;
import com.example.demo.model.Specialization;

public class SpecializationMapper {

    public static Specialization toEntity(SpecializationRequestDto dto) {
        return Specialization.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .defaultDuration(dto.getDefaultDuration())
                .build();
    }

    public static SpecializationResponseDto toDto(Specialization entity) {
        SpecializationResponseDto dto = new SpecializationResponseDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setDefaultDuration(entity.getDefaultDuration());
        return dto;
    }

    public static void updateEntity(Specialization entity, SpecializationRequestDto dto) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setDefaultDuration(dto.getDefaultDuration());
    }

}
