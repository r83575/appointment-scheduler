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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
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

        RoomSpecializationId id = new RoomSpecializationId(roomId, specializationId);

        RoomSpecialization entity = repository.findById(id)
                .orElseThrow(() -> {
                    log.warn(
                            "RoomSpecialization not found: roomId={}, specializationId={}",
                            roomId,
                            specializationId
                    );
                    return new ResourceNotFoundException("RoomSpecialization not found");
                });

        return RoomSpecializationMapper.toDto(entity);
    }

    public RoomSpecializationResponseDto create(RoomSpecializationRequestDto dto) {

        log.info(
                "Creating RoomSpecialization: roomId={}, specializationId={}",
                dto.getRoomId(),
                dto.getSpecializationId()
        );

        RoomSpecialization entity = RoomSpecializationMapper.toEntity(dto);

        RoomSpecializationId id = new RoomSpecializationId(
                entity.getRoom().getId(),
                entity.getSpecialization().getId()
        );

        if (repository.existsById(id)) {
            log.warn(
                    "RoomSpecialization already exists: roomId={}, specializationId={}",
                    entity.getRoom().getId(),
                    entity.getSpecialization().getId()
            );
            throw new ConflictException("This RoomSpecialization already exists");
        }

        RoomSpecialization saved = repository.save(entity);

        log.info(
                "RoomSpecialization created successfully: roomId={}, specializationId={}",
                saved.getRoom().getId(),
                saved.getSpecialization().getId()
        );

        return RoomSpecializationMapper.toDto(saved);
    }

    public void delete(Long roomId, Long specializationId) {

        log.info(
                "Deleting RoomSpecialization: roomId={}, specializationId={}",
                roomId,
                specializationId
        );

        RoomSpecializationId id = new RoomSpecializationId(roomId, specializationId);
        repository.deleteById(id);
    }
}
