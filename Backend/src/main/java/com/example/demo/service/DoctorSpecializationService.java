package com.example.demo.service;

import com.example.demo.model.DoctorSpecialization;
import com.example.demo.model.DoctorSpecializationId;
import com.example.demo.repository.DoctorSpecializationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorSpecializationService {

    private final DoctorSpecializationRepository repository;

    public DoctorSpecializationService(DoctorSpecializationRepository repository) {
        this.repository = repository;
    }

    public List<DoctorSpecialization> getAll() {
        return repository.findAll();
    }

    public Optional<DoctorSpecialization> getById(Long doctorId, Long specializationId) {
        DoctorSpecializationId id = new DoctorSpecializationId(doctorId, specializationId);
        return repository.findById(id);
    }

    public DoctorSpecialization save(DoctorSpecialization doctorSpecialization) {
        return repository.save(doctorSpecialization);
    }

    public void delete(Long doctorId, Long specializationId) {
        DoctorSpecializationId id = new DoctorSpecializationId(doctorId, specializationId);
        repository.deleteById(id);
    }
}
