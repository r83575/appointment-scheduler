package com.example.demo.dto.specialization;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Max;
import lombok.Data;

@Data
public class SpecializationRequestDto {

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @NotBlank(message = "Description is required")
    @Size(max = 255, message = "Description must not exceed 255 characters")
    private String description;

    @NotNull(message = "Default duration is required")
    @Min(value = 1, message = "Default duration must be at least 1 minute")
    @Max(value = 100, message = "Default duration must not exceed 100 minutes")
    private Integer defaultDuration;
}
