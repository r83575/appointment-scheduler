package com.example.demo.mapper;

import com.example.demo.dto.roomspecialization.RoomSpecializationRequestDto;
import com.example.demo.dto.roomspecialization.RoomSpecializationResponseDto;
import com.example.demo.model.RoomSpecialization;

public class RoomSpecializationMapper {

    public static RoomSpecialization toEntity(RoomSpecializationRequestDto dto) {
        return RoomSpecialization.builder()
                .roomId(dto.getRoomId())
                .specializationId(dto.getSpecializationId())
                .build();
    }

    public static RoomSpecializationResponseDto toDto(RoomSpecialization entity) {
        RoomSpecializationResponseDto dto = new RoomSpecializationResponseDto();
        dto.setRoomId(entity.getRoomId());
        dto.setSpecializationId(entity.getSpecializationId());
        return dto;
    }

    public static void updateEntity(RoomSpecialization entity, RoomSpecializationRequestDto dto) {
        entity.setRoomId(dto.getRoomId());
        entity.setSpecializationId(dto.getSpecializationId());

    }
}
