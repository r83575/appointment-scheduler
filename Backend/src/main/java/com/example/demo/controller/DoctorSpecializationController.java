package com.example.demo.controller;

import com.example.demo.model.DoctorSpecialization;
import com.example.demo.service.DoctorSpecializationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctorSpecializations")
public class DoctorSpecializationController {

    private final DoctorSpecializationService service;

    public DoctorSpecializationController(DoctorSpecializationService service) {
        this.service = service;
    }

    @GetMapping
    public List<DoctorSpecialization> getAll() {
        return service.getAll();
    }

    @GetMapping("/{doctorId}/{specializationId}")
    public DoctorSpecialization getById(@PathVariable Long doctorId,
                                        @PathVariable Long specializationId) {
        return service.getById(doctorId, specializationId).orElse(null);
    }

    @PostMapping
    public DoctorSpecialization create(@RequestBody DoctorSpecialization doctorSpecialization) {
        return service.save(doctorSpecialization);
    }

    @DeleteMapping("/{doctorId}/{specializationId}")
    public void delete(@PathVariable Long doctorId,
                       @PathVariable Long specializationId) {
        service.delete(doctorId, specializationId);
    }
}
