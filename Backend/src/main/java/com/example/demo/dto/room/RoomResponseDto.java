package com.example.demo.dto.room;

import lombok.Data;

@Data
public class RoomResponseDto {
    private Long id;
    private int roomNumber;
    private String location;
    private boolean isActive;
}