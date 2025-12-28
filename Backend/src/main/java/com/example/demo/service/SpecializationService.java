package com.example.demo.service;

import com.example.demo.dto.specialization.SpecializationRequestDto;
import com.example.demo.dto.specialization.SpecializationResponseDto;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.SpecializationMapper;
import com.example.demo.model.Specialization;
import com.example.demo.repository.SpecializationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SpecializationService {

    private final SpecializationRepository repository;

    public List<SpecializationResponseDto> getAll() {
        return repository.findAll()
                .stream()
                .map(SpecializationMapper::toDto)
                .toList();
    }

    public SpecializationResponseDto getById(Long id) {

        Specialization entity = repository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Specialization not found: id={}", id);
                    return new ResourceNotFoundException("Specialization not found");
                });

        return SpecializationMapper.toDto(entity);
    }

    public SpecializationResponseDto create(SpecializationRequestDto dto) {

        log.info("Creating specialization");

        Specialization entity = SpecializationMapper.toEntity(dto);
        Specialization saved = repository.save(entity);

        log.info("Specialization created successfully: specializationId={}", saved.getId());

        return SpecializationMapper.toDto(saved);
    }

    public SpecializationResponseDto update(Long id, SpecializationRequestDto dto) {

        log.info("Updating specialization: id={}", id);

        Specialization existing = repository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Specialization not found: id={}", id);
                    return new ResourceNotFoundException("Specialization not found");
                });

        SpecializationMapper.updateEntity(existing, dto);

        Specialization updated = repository.save(existing);

        log.info("Specialization updated successfully: specializationId={}", updated.getId());

        return SpecializationMapper.toDto(updated);
    }

    public void delete(Long id) {
        log.info("Deleting specialization: id={}", id);
        repository.deleteById(id);
    }
}
