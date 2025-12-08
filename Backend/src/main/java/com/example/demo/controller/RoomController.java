package com.example.demo.controller;

import com.example.demo.dto.room.RoomRequestDto;
import com.example.demo.dto.room.RoomResponseDto;
import com.example.demo.service.RoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService service;

    @GetMapping
    public List<RoomResponseDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public RoomResponseDto getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public RoomResponseDto create(@Valid @RequestBody RoomRequestDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public RoomResponseDto update(@PathVariable Long id, @Valid  @RequestBody RoomRequestDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
