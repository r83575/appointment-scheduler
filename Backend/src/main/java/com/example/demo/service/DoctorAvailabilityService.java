package com.example.demo.service;

import com.example.demo.dto.availability.DoctorAvailabilityRequestDto;
import com.example.demo.dto.availability.DoctorAvailabilityResponseDto;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.DoctorAvailabilityMapper;
import com.example.demo.model.DoctorAvailability;
import com.example.demo.repository.DoctorAvailabilityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DoctorAvailabilityService {

    private final DoctorAvailabilityRepository repository;

    public List<DoctorAvailabilityResponseDto> getAll() {
        return repository.findAll()
                .stream()
                .map(DoctorAvailabilityMapper::toDto)
                .toList();
    }

    public DoctorAvailabilityResponseDto getById(Long id) {

        DoctorAvailability entity = repository.findById(id)
                .orElseThrow(() -> {
                    log.warn("DoctorAvailability not found: id={}", id);
                    return new ResourceNotFoundException("DoctorAvailability not found");
                });

        return DoctorAvailabilityMapper.toDto(entity);
    }

    public DoctorAvailabilityResponseDto create(DoctorAvailabilityRequestDto dto) {

        log.info("Creating doctor availability");

        DoctorAvailability entity = DoctorAvailabilityMapper.toEntity(dto);
        DoctorAvailability saved = repository.save(entity);

        log.info("DoctorAvailability created successfully: id={}", saved.getId());

        return DoctorAvailabilityMapper.toDto(saved);
    }

    public DoctorAvailabilityResponseDto update(Long id, DoctorAvailabilityRequestDto dto) {

        log.info("Updating doctor availability: id={}", id);

        DoctorAvailability existing = repository.findById(id)
                .orElseThrow(() -> {
                    log.warn("DoctorAvailability not found: id={}", id);
                    return new ResourceNotFoundException("DoctorAvailability not found");
                });

        DoctorAvailabilityMapper.updateEntity(existing, dto);

        DoctorAvailability updated = repository.save(existing);

        log.info("DoctorAvailability updated successfully: id={}", updated.getId());

        return DoctorAvailabilityMapper.toDto(updated);
    }

    public void delete(Long id) {
        log.info("Deleting doctor availability: id={}", id);
        repository.deleteById(id);
    }
}
