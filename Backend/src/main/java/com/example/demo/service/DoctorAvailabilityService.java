package com.example.demo.service;

import com.example.demo.model.DoctorAvailability;
import com.example.demo.repository.DoctorAvailabilityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorAvailabilityService {

    private final DoctorAvailabilityRepository repository;

    public DoctorAvailabilityService(DoctorAvailabilityRepository repository) {
        this.repository = repository;
    }

    public List<DoctorAvailability> getAll() {
        return repository.findAll();
    }

    public Optional<DoctorAvailability> getById(Long id) {
        return repository.findById(id);
    }

    public DoctorAvailability save(DoctorAvailability doctorAvailability) {
        return repository.save(doctorAvailability);
    }

    public DoctorAvailability update(Long id, DoctorAvailability updated) {
        return repository.findById(id).map(existing -> {
            existing.setDate(updated.getDate());
            existing.setStartTime(updated.getStartTime());
            existing.setEndTime(updated.getEndTime());
            return repository.save(existing);
        }).orElseThrow(() -> new RuntimeException("DoctorAvailability not found"));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
