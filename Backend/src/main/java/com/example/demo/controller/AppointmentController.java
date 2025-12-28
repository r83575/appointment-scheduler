package com.example.demo.controller;

import com.example.demo.dto.appointment.AppointmentRequestDto;
import com.example.demo.dto.appointment.AppointmentResponseDto;
import com.example.demo.dto.page.PageResponseDto;
import com.example.demo.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentService service;

    @GetMapping
    public PageResponseDto<AppointmentResponseDto> getAll(Pageable pageable) {
        return service.getAll(pageable);
    }

    @GetMapping("/{id}")
    public AppointmentResponseDto getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public AppointmentResponseDto create(@RequestBody AppointmentRequestDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public AppointmentResponseDto update(
            @PathVariable Long id,
            @RequestBody AppointmentRequestDto dto
    ) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
