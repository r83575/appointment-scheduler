package com.example.demo.mapper;

import com.example.demo.dto.room.*;
import com.example.demo.model.Room;

public class RoomMapper {

    public static Room toEntity(RoomRequestDto dto) {
        return Room.builder()
                .roomNumber(dto.getRoomNumber())
                .location(dto.getLocation())
                .isActive(dto.isActive())
                .build();
    }

    public static RoomResponseDto toDto(Room entity) {
        RoomResponseDto dto = new RoomResponseDto();
        dto.setId(entity.getId());
        dto.setRoomNumber(entity.getRoomNumber());
        dto.setLocation(entity.getLocation());
        dto.setActive(entity.isActive());
        return dto;
    }

    public static void updateEntity(Room entity, RoomRequestDto dto) {
        entity.setRoomNumber(dto.getRoomNumber());
        entity.setLocation(dto.getLocation());
        entity.setActive(dto.isActive());
    }
}
