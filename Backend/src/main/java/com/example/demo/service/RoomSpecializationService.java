package com.example.demo.service;

import com.example.demo.model.RoomSpecialization;
import com.example.demo.model.RoomSpecializationId;
import com.example.demo.repository.RoomSpecializationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomSpecializationService {

    private final RoomSpecializationRepository repository;

    public List<RoomSpecialization> getAll() {
        return repository.findAll();
    }

    public Optional<RoomSpecialization> getById(Long roomId, Long specializationId) {
        return repository.findById(new RoomSpecializationId(roomId, specializationId));
    }

    public RoomSpecialization save(RoomSpecialization entity) {
        RoomSpecializationId id = new RoomSpecializationId(
                entity.getRoom().getId(),
                entity.getSpecialization().getId()
        );

        if (repository.existsById(id)) {
            throw new RuntimeException("This RoomSpecialization already exists");
        }

        return repository.save(entity);
    }

    public void delete(Long roomId, Long specializationId) {
        repository.deleteById(new RoomSpecializationId(roomId, specializationId));
    }
}
