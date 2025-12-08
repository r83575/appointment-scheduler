package com.example.demo.controller;

import com.example.demo.dto.availability.DoctorAvailabilityRequestDto;
import com.example.demo.dto.availability.DoctorAvailabilityResponseDto;
import com.example.demo.service.DoctorAvailabilityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctor-availability")
@RequiredArgsConstructor
public class DoctorAvailabilityController {

    private final DoctorAvailabilityService service;

    // GET ALL
    @GetMapping
    public List<DoctorAvailabilityResponseDto> getAll() {
        return service.getAll();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public DoctorAvailabilityResponseDto getById(@PathVariable Long id) {
        return service.getById(id);
    }

    // CREATE
    @PostMapping
    public DoctorAvailabilityResponseDto create(
            @RequestBody @Valid DoctorAvailabilityRequestDto dto
    ) {
        return service.create(dto);
    }

    // UPDATE
    @PutMapping("/{id}")
    public DoctorAvailabilityResponseDto update(
            @PathVariable Long id,
            @RequestBody DoctorAvailabilityRequestDto dto
    ) {
        return service.update(id, dto);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
