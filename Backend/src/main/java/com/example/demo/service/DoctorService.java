package com.example.demo.service;

import com.example.demo.dto.doctor.DoctorRequestDto;
import com.example.demo.dto.doctor.DoctorResponseDto;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.DoctorMapper;
import com.example.demo.model.Doctor;
import com.example.demo.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository repository;

    public List<DoctorResponseDto> getAll() {
        return repository.findAll()
                .stream()
                .map(DoctorMapper::toDto)
                .toList();
    }

    public DoctorResponseDto getById(Long id) {

        Doctor entity = repository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Doctor not found: id={}", id);
                    return new ResourceNotFoundException("Doctor not found");
                });

        return DoctorMapper.toDto(entity);
    }

    public DoctorResponseDto create(DoctorRequestDto dto) {

        log.info("Creating doctor");

        Doctor entity = DoctorMapper.toEntity(dto);
        Doctor saved = repository.save(entity);

        log.info("Doctor created successfully: doctorId={}", saved.getId());

        return DoctorMapper.toDto(saved);
    }

    public DoctorResponseDto update(Long id, DoctorRequestDto dto) {

        log.info("Updating doctor: id={}", id);

        Doctor entity = repository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Doctor not found: id={}", id);
                    return new ResourceNotFoundException("Doctor not found");
                });

        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());

        Doctor saved = repository.save(entity);

        log.info("Doctor updated successfully: doctorId={}", saved.getId());

        return DoctorMapper.toDto(saved);
    }

    public void delete(Long id) {
        log.info("Deleting doctor: id={}", id);
        repository.deleteById(id);
    }
}
