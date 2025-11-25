package com.example.demo.service;

import com.example.demo.model.Appointment;
import com.example.demo.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    private final AppointmentRepository repository;

    public AppointmentService(AppointmentRepository repository) {
        this.repository = repository;
    }

    public List<Appointment> getAll() {
        return repository.findAll();
    }

    public Optional<Appointment> getById(Long id) {
        return repository.findById(id);
    }

    public Appointment save(Appointment appointment) {
        return repository.save(appointment);
    }

    public Appointment update(Long id, Appointment updated) {
        return repository.findById(id).map(existing -> {
            existing.setDoctor(updated.getDoctor());
            existing.setCustomer(updated.getCustomer());
            existing.setRoom(updated.getRoom());
            existing.setSpecialization(updated.getSpecialization());
            existing.setDate(updated.getDate());
            existing.setStartTime(updated.getStartTime());
            existing.setEndTime(updated.getEndTime());
            existing.setStatus(updated.getStatus());
            return repository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Appointment not found"));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
