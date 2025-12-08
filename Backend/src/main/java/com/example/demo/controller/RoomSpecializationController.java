package com.example.demo.controller;

import com.example.demo.dto.roomspecialization.*;
import com.example.demo.mapper.RoomSpecializationMapper;
import com.example.demo.model.RoomSpecialization;
import com.example.demo.service.RoomSpecializationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/roomSpecializations")
@RequiredArgsConstructor
public class RoomSpecializationController {

    private final RoomSpecializationService service;

    @GetMapping
    public List<RoomSpecializationResponseDto> getAll() {
        return service.getAll().stream()
                .map(RoomSpecializationMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{roomId}/{specializationId}")
    public RoomSpecializationResponseDto getById(@PathVariable Long roomId,
                                                 @PathVariable Long specializationId) {
        Optional<RoomSpecialization> optional = service.getById(roomId, specializationId);
        return optional.map(RoomSpecializationMapper::toDto).orElse(null);
    }

    @PostMapping
    public RoomSpecializationResponseDto create(@RequestBody @Valid RoomSpecializationRequestDto requestDto) {
        RoomSpecialization entity = RoomSpecializationMapper.toEntity(requestDto);
        RoomSpecialization saved = service.save(entity);
        return RoomSpecializationMapper.toDto(saved);
    }

    @DeleteMapping("/{roomId}/{specializationId}")
    public void delete(@PathVariable Long roomId,
                       @PathVariable Long specializationId) {
        service.delete(roomId, specializationId);
    }
}
