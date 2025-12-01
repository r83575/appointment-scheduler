package com.example.demo.service;

import com.example.demo.dto.doctor.DoctorRequestDto;
import com.example.demo.model.Doctor;
import com.example.demo.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    private final DoctorRepository repository;

    public DoctorService(DoctorRepository repository) {
        this.repository = repository;
    }

    public List<Doctor> getAll() {
        return repository.findAll();
    }

    public Optional<Doctor> getById(Long id) {
        return repository.findById(id);
    }

    public Doctor save(Doctor doctor) {
        return repository.save(doctor);
    }

    public Doctor update(Long id, DoctorRequestDto dto) {
        return repository.findById(id).map(existing -> {
            existing.setFirstName(dto.getFirstName());
            existing.setLastName(dto.getLastName());
            return repository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Doctor not found"));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
