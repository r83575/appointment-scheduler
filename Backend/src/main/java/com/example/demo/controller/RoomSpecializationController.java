package com.example.demo.controller;

import com.example.demo.dto.roomspecialization.*;
import com.example.demo.service.RoomSpecializationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/room-specializations")
public class RoomSpecializationController {

    private final RoomSpecializationService service;

    @GetMapping
    public List<RoomSpecializationResponseDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{roomId}/{specializationId}")
    public RoomSpecializationResponseDto getById(@PathVariable Long roomId,
                                                 @PathVariable Long specializationId) {
        return service.getById(roomId, specializationId);
    }

    @PostMapping
    public RoomSpecializationResponseDto create(@RequestBody @Valid RoomSpecializationRequestDto requestDto) {
        return service.create(requestDto);
    }

    @DeleteMapping("/{roomId}/{specializationId}")
    public void delete(@PathVariable Long roomId,
                       @PathVariable Long specializationId) {
        service.delete(roomId, specializationId);
    }
}
