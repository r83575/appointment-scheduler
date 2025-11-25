package com.example.demo.service;

import com.example.demo.model.Room;
import com.example.demo.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    private final RoomRepository repository;

    public RoomService(RoomRepository repository) {
        this.repository = repository;
    }

    public List<Room> getAll() {
        return repository.findAll();
    }

    public Optional<Room> getById(Long id) {
        return repository.findById(id);
    }

    public Room save(Room room) {
        return repository.save(room);
    }

    public Room update(Long id, Room updated) {
        return repository.findById(id).map(existing -> {
            existing.setRoomNumber(updated.getRoomNumber());
            existing.setLocation(updated.getLocation());
            existing.setActive(updated.isActive());
            return repository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Room not found"));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
