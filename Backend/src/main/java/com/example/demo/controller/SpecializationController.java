package com.example.demo.controller;

import com.example.demo.model.Specialization;
import com.example.demo.service.SpecializationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/specializations")
public class SpecializationController {

    private final SpecializationService service;

    public SpecializationController(SpecializationService service) {
        this.service = service;
    }

    @GetMapping
    public List<Specialization> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Specialization getById(@PathVariable Long id) {
        return service.getById(id).orElse(null);
    }

    @PostMapping
    public Specialization create(@RequestBody Specialization specialization) {
        return service.save(specialization);
    }

    @PutMapping("/{id}")
    public Specialization update(@PathVariable Long id, @RequestBody Specialization updated) {
        return service.update(id, updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
