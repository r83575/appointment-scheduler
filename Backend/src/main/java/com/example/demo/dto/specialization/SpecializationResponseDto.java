package com.example.demo.dto.specialization;

import lombok.Data;

@Data
public class SpecializationResponseDto {
    private Long id;
    private String name;
    private String description;
    private int defaultDuration;
}
