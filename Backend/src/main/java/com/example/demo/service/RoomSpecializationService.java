package com.example.demo.service;

import com.example.demo.dto.roomspecialization.RoomSpecializationRequestDto;
import com.example.demo.dto.roomspecialization.RoomSpecializationResponseDto;
import com.example.demo.exception.ConflictException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.RoomSpecializationMapper;
import com.example.demo.model.RoomSpecialization;
import com.example.demo.model.RoomSpecializationId;
import com.example.demo.repository.RoomSpecializationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RoomSpecializationService {

    private final RoomSpecializationRepository repository;

    public List<RoomSpecializationResponseDto> getAll() {
        return repository.findAll()
                .stream()
                .map(RoomSpecializationMapper::toDto)
                .toList();
    }

    public RoomSpecializationResponseDto getById(Long roomId, Long specializationId) {
        RoomSpecialization entity = repository
                .findById(new RoomSpecializationId(roomId, specializationId))
                .orElseThrow(() -> new ResourceNotFoundException("RoomSpecialization not found"));

        return RoomSpecializationMapper.toDto(entity);
    }

    public RoomSpecializationResponseDto create(RoomSpecializationRequestDto dto) {
        RoomSpecialization entity = RoomSpecializationMapper.toEntity(dto);

        RoomSpecializationId id = new RoomSpecializationId(
                entity.getRoom().getId(),
                entity.getSpecialization().getId()
        );

        if (repository.existsById(id)) {
            throw new ConflictException("This RoomSpecialization already exists");
        }

        RoomSpecialization saved = repository.save(entity);
        return RoomSpecializationMapper.toDto(saved);
    }

    public void delete(Long roomId, Long specializationId) {
        repository.deleteById(new RoomSpecializationId(roomId, specializationId));
    }
}
