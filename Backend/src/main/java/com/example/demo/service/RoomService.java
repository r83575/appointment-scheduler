package com.example.demo.service;

import com.example.demo.dto.room.RoomRequestDto;
import com.example.demo.dto.room.RoomResponseDto;
import com.example.demo.exception.ConflictException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.RoomMapper;
import com.example.demo.model.Room;
import com.example.demo.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository repository;

    public List<RoomResponseDto> getAll() {
        return repository.findAll().stream()
                .map(RoomMapper::toDto)
                .toList();
    }

    public RoomResponseDto getById(Long id) {
        Room entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found"));

        return RoomMapper.toDto(entity);
    }

    @Transactional
    public RoomResponseDto create(RoomRequestDto dto) {

        if (repository.existsByRoomNumber(dto.getRoomNumber())) {
            throw new ConflictException("Room number already exists");
        }

        Room entity = RoomMapper.toEntity(dto);
        Room saved = repository.save(entity);
        return RoomMapper.toDto(saved);
    }

    @Transactional
    public RoomResponseDto update(Long id, RoomRequestDto dto) {

        return repository.findById(id)
                .map(existing -> {

                    if (existing.getRoomNumber() != dto.getRoomNumber() &&
                            repository.existsByRoomNumber(dto.getRoomNumber())) {
                        throw new ConflictException("Room number already exists");
                    }

                    RoomMapper.updateEntity(existing, dto);
                    Room updated = repository.save(existing);
                    return RoomMapper.toDto(updated);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Room not found"));
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
