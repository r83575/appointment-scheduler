package com.example.demo.controller;

import com.example.demo.dto.doctor.DoctorRequestDto;
import com.example.demo.dto.doctor.DoctorResponseDto;
import com.example.demo.service.DoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/doctors")
public class DoctorController {

    private final DoctorService service;

    @GetMapping
    public List<DoctorResponseDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public DoctorResponseDto getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public DoctorResponseDto create(@Valid @RequestBody DoctorRequestDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public DoctorResponseDto update(@PathVariable Long id,
                                    @Valid @RequestBody DoctorRequestDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
