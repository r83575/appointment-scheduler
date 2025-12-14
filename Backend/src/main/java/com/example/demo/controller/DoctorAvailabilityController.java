package com.example.demo.controller;

import com.example.demo.dto.availability.DoctorAvailabilityRequestDto;
import com.example.demo.dto.availability.DoctorAvailabilityResponseDto;
import com.example.demo.service.DoctorAvailabilityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/doctor-availability")
public class DoctorAvailabilityController {

    private final DoctorAvailabilityService service;

    @GetMapping
    public List<DoctorAvailabilityResponseDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public DoctorAvailabilityResponseDto getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public DoctorAvailabilityResponseDto create(
            @RequestBody @Valid DoctorAvailabilityRequestDto dto
    ) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public DoctorAvailabilityResponseDto update(
            @PathVariable Long id,
            @RequestBody DoctorAvailabilityRequestDto dto
    ) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
