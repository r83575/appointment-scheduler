package com.example.demo.service;

import com.example.demo.model.RoomSpecialization;
import com.example.demo.model.RoomSpecializationId;
import com.example.demo.repository.RoomSpecializationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomSpecializationService {

    private final RoomSpecializationRepository repository;

    public RoomSpecializationService(RoomSpecializationRepository repository) {
        this.repository = repository;
    }

    public List<RoomSpecialization> getAll() {
        return repository.findAll();
    }

    public Optional<RoomSpecialization> getById(Long roomId, Long specializationId) {
        RoomSpecializationId id = new RoomSpecializationId(roomId, specializationId);
        return repository.findById(id);
    }

    public RoomSpecialization save(RoomSpecialization roomSpecialization) {
        return repository.save(roomSpecialization);
    }

    public void delete(Long roomId, Long specializationId) {
        RoomSpecializationId id = new RoomSpecializationId(roomId, specializationId);
        repository.deleteById(id);
    }
}
