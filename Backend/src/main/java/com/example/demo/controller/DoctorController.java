package com.example.demo.controller;

import com.example.demo.dto.doctor.DoctorRequestDto;
import com.example.demo.dto.doctor.DoctorResponseDto;
import com.example.demo.mapper.DoctorMapper;
import com.example.demo.model.Doctor;
import com.example.demo.service.DoctorService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    private final DoctorService service;

    public DoctorController(DoctorService service) {
        this.service = service;
    }

    @GetMapping
    public List<DoctorResponseDto> getAll() {
        return service.getAll()
                .stream()
                .map(DoctorMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public DoctorResponseDto getById(@PathVariable Long id) {
        return service.getById(id)
                .map(DoctorMapper::toDto)
                .orElse(null);
    }

    @PostMapping
    public DoctorResponseDto create(@Valid @RequestBody DoctorRequestDto dto) {
        Doctor doctor = DoctorMapper.toEntity(dto);
        Doctor saved = service.save(doctor);
        return DoctorMapper.toDto(saved);
    }

    @PutMapping("/{id}")
    public DoctorResponseDto update(@PathVariable Long id,
                                    @Valid @RequestBody DoctorRequestDto dto) {
        Doctor saved = service.update(id, dto);
        return DoctorMapper.toDto(saved);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
