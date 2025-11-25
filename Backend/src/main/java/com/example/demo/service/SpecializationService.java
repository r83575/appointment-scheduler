package com.example.demo.service;

import com.example.demo.model.Specialization;
import com.example.demo.repository.SpecializationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpecializationService {

    private final SpecializationRepository repository;

    public SpecializationService(SpecializationRepository repository) {
        this.repository = repository;
    }

    public List<Specialization> getAll() {
        return repository.findAll();
    }

    public Optional<Specialization> getById(Long id) {
        return repository.findById(id);
    }

    public Specialization save(Specialization specialization) {
        return repository.save(specialization);
    }

    public Specialization update(Long id, Specialization updated) {
        return repository.findById(id).map(existing -> {
            existing.setName(updated.getName());
            existing.setDescription(updated.getDescription());
            existing.setDefaultDuration(updated.getDefaultDuration());
            return repository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Specialization not found"));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
