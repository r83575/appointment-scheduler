package com.example.demo.service;

import com.example.demo.dto.room.RoomRequestDto;
import com.example.demo.dto.room.RoomResponseDto;
import com.example.demo.mapper.RoomMapper;
import com.example.demo.model.Room;
import com.example.demo.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository repository;

    public List<RoomResponseDto> getAll() {
        return repository.findAll().stream()
                .map(RoomMapper::toDto)
                .collect(Collectors.toList());
    }

    public RoomResponseDto getById(Long id) {
        return repository.findById(id)
                .map(RoomMapper::toDto)
                .orElse(null);
    }

    public RoomResponseDto create(RoomRequestDto dto) {
        Room entity = RoomMapper.toEntity(dto);
        Room saved = repository.save(entity);
        return RoomMapper.toDto(saved);
    }

    public RoomResponseDto update(Long id, RoomRequestDto dto) {
        return repository.findById(id)
                .map(existing -> {
                    RoomMapper.updateEntity(existing, dto);
                    return RoomMapper.toDto(repository.save(existing));
                })
                .orElseThrow(() -> new RuntimeException("Room not found"));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
