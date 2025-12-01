package com.example.demo.controller;

import com.example.demo.model.DoctorAvailability;
import com.example.demo.service.DoctorAvailabilityService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctorAvailability")
public class DoctorAvailabilityController {

    private final DoctorAvailabilityService service;

    public DoctorAvailabilityController(DoctorAvailabilityService service) {
        this.service = service;
    }

    @GetMapping
    public List<DoctorAvailability> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public DoctorAvailability getById(@PathVariable Long id) {
        return service.getById(id).orElse(null);
    }

    @PostMapping
    public DoctorAvailability create(@RequestBody DoctorAvailability doctorAvailability) {
        return service.save(doctorAvailability);
    }

    @PutMapping("/{id}")
    public DoctorAvailability update(@PathVariable Long id, @RequestBody DoctorAvailability updated) {
        return service.update(id, updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
