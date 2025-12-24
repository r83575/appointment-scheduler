package com.example.demo.service;

import com.example.demo.dto.room.RoomRequestDto;
import com.example.demo.dto.room.RoomResponseDto;
import com.example.demo.exception.ConflictException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.RoomMapper;
import com.example.demo.model.Room;
import com.example.demo.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
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
                .orElseThrow(() -> {
                    log.warn("Room not found: id={}", id);
                    return new ResourceNotFoundException("Room not found");
                });

        return RoomMapper.toDto(entity);
    }

    @Transactional
    public RoomResponseDto create(RoomRequestDto dto) {

        log.info("Creating room: roomNumber={}", dto.getRoomNumber());

        if (repository.existsByRoomNumber(dto.getRoomNumber())) {
            log.warn("Room number already exists: roomNumber={}", dto.getRoomNumber());
            throw new ConflictException("Room number already exists");
        }

        Room entity = RoomMapper.toEntity(dto);
        Room saved = repository.save(entity);

        log.info("Room created successfully: roomId={}, roomNumber={}",
                saved.getId(),
                saved.getRoomNumber()
        );

        return RoomMapper.toDto(saved);
    }

    @Transactional
    public RoomResponseDto update(Long id, RoomRequestDto dto) {

        log.info("Updating room: id={}", id);

        return repository.findById(id)
                .map(existing -> {

                    if (existing.getRoomNumber() != dto.getRoomNumber()
                            && repository.existsByRoomNumber(dto.getRoomNumber())) {

                        log.warn(
                                "Room number conflict on update: id={}, roomNumber={}",
                                id,
                                dto.getRoomNumber()
                        );
                        throw new ConflictException("Room number already exists");
                    }

                    RoomMapper.updateEntity(existing, dto);
                    Room updated = repository.save(existing);

                    log.info(
                            "Room updated successfully: roomId={}, roomNumber={}",
                            updated.getId(),
                            updated.getRoomNumber()
                    );

                    return RoomMapper.toDto(updated);
                })
                .orElseThrow(() -> {
                    log.warn("Room not found for update: id={}", id);
                    return new ResourceNotFoundException("Room not found");
                });
    }

    @Transactional
    public void delete(Long id) {
        log.info("Deleting room: id={}", id);
        repository.deleteById(id);
    }
}
