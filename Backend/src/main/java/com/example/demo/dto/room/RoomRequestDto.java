package com.example.demo.dto.room;

import lombok.Data;
import java.util.Set;

@Data
public class RoomRequestDto {
    private int roomNumber;
    private String location;
    private boolean isActive;
}