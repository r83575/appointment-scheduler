package com.example.demo.controller;

import com.example.demo.model.RoomSpecialization;
import com.example.demo.service.RoomSpecializationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roomSpecializations")
public class RoomSpecializationController {

    private final RoomSpecializationService service;

    public RoomSpecializationController(RoomSpecializationService service) {
        this.service = service;
    }
    @GetMapping
    public List<RoomSpecialization> getAll() {
        return service.getAll();
    }

    @GetMapping("/{roomId}/{specializationId}")
    public RoomSpecialization getById(@PathVariable Long roomId,
                                      @PathVariable Long specializationId) {
        return service.getById(roomId,specializationId).orElse(null);
    }

    @PostMapping
    public RoomSpecialization create(@RequestBody RoomSpecialization roomSpecialization) {
        return service.save(roomSpecialization);
    }

    @DeleteMapping("/{roomId}/{specializationId}")
    public void delete(@PathVariable Long roomId,
                       @PathVariable Long specializationId) {
        service.delete(roomId,specializationId);
    }

}
