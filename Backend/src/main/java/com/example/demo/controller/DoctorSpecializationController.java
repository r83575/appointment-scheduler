package com.example.demo.controller;

import com.example.demo.dto.doctorspecialization.*;
import com.example.demo.service.DoctorSpecializationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctor-specializations")
@RequiredArgsConstructor
public class DoctorSpecializationController {

    private final DoctorSpecializationService service;

    // GET ALL
    @GetMapping
    public List<DoctorSpecializationResponseDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{doctorId}/{specializationId}")
    public DoctorSpecializationResponseDto getById(
            @PathVariable Long doctorId,
            @PathVariable Long specializationId
    ) {
        return service.getById(doctorId, specializationId);
    }

    @PostMapping
    public DoctorSpecializationResponseDto create(
           @Valid @RequestBody DoctorSpecializationRequestDto dto
    ) {
        return service.create(dto);
    }

    @PutMapping("/{doctorId}/{specializationId}")
    public DoctorSpecializationResponseDto update(@Valid
            @PathVariable Long doctorId,
            @PathVariable Long specializationId,
            @RequestBody DoctorSpecializationRequestDto dto
    ) {
        return service.update(doctorId, specializationId, dto);
    }

    @DeleteMapping("/{doctorId}/{specializationId}")
    public void delete(
            @PathVariable Long doctorId,
            @PathVariable Long specializationId
    ) {
        service.delete(doctorId, specializationId);
    }
}
