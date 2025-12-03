package com.example.demo.dto.specialization;

import lombok.Data;

@Data
public class SpecializationRequestDto {
    private String name;
    private String description;
    private int defaultDuration;
}
