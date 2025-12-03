package com.example.demo.service;

import com.example.demo.dto.availability.DoctorAvailabilityRequestDto;
import com.example.demo.dto.availability.DoctorAvailabilityResponseDto;
import com.example.demo.mapper.DoctorAvailabilityMapper;
import com.example.demo.model.DoctorAvailability;
import com.example.demo.repository.DoctorAvailabilityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
                .orElseThrow(() -> new RuntimeException("DoctorAvailability not found"));

        return DoctorAvailabilityMapper.toDto(entity);
    }

    public DoctorAvailabilityResponseDto create(DoctorAvailabilityRequestDto dto) {
        DoctorAvailability entity = DoctorAvailabilityMapper.toEntity(dto);
        DoctorAvailability saved = repository.save(entity);

        return DoctorAvailabilityMapper.toDto(saved);
    }

    public DoctorAvailabilityResponseDto update(Long id, DoctorAvailabilityRequestDto dto) {
        DoctorAvailability existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("DoctorAvailability not found"));

        DoctorAvailabilityMapper.updateEntity(existing, dto);

        DoctorAvailability updated = repository.save(existing);

        return DoctorAvailabilityMapper.toDto(updated);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
