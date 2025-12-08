package com.example.demo.dto.room;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RoomRequestDto {

    @Min(value = 1, message = "roomNumber must be >= 1")
    private int roomNumber;

    @NotBlank(message = "location is required")
    private String location;

    private boolean isActive;
}
