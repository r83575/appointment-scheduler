package com.example.demo.controller;

import com.example.demo.model.Room;
import com.example.demo.service.RoomService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    private final RoomService service;

    public RoomController(RoomService service) {
        this.service = service;
    }

    @GetMapping
    public List<Room> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Room getById(@PathVariable Long id) {
        return service.getById(id).orElse(null);
    }

    @PostMapping
    public Room create(@RequestBody Room room) {
        return service.save(room);
    }

    @PutMapping("/{id}")
    public Room update(@PathVariable Long id, @RequestBody Room updated) {
        return service.update(id, updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
