package com.example.demo.controller;

import com.example.demo.dto.appointment.AppointmentRequestDto;
import com.example.demo.dto.appointment.AppointmentResponseDto;
import com.example.demo.service.AppointmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentService service;

    public AppointmentController(AppointmentService service) {
        this.service = service;
    }

    @GetMapping
    public List<AppointmentResponseDto> getAll() {
        return service.getAll();
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
