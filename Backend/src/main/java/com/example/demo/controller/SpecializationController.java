package com.example.demo.controller;

import com.example.demo.dto.specialization.SpecializationRequestDto;
import com.example.demo.dto.specialization.SpecializationResponseDto;
import com.example.demo.service.SpecializationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/specializations")
@RequiredArgsConstructor
public class SpecializationController {

    private final SpecializationService service;

    @GetMapping
    public List<SpecializationResponseDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public SpecializationResponseDto getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public SpecializationResponseDto create(@RequestBody SpecializationRequestDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public SpecializationResponseDto update(
            @PathVariable Long id,
            @RequestBody SpecializationRequestDto dto
    ) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
