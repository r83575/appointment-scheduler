package com.example.demo.service;

import com.example.demo.dto.specialization.SpecializationRequestDto;
import com.example.demo.dto.specialization.SpecializationResponseDto;
import com.example.demo.mapper.SpecializationMapper;
import com.example.demo.model.Specialization;
import com.example.demo.repository.SpecializationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
                .orElseThrow(() -> new RuntimeException("Specialization not found"));

        return SpecializationMapper.toDto(entity);
    }

    public SpecializationResponseDto create(SpecializationRequestDto dto) {
        Specialization entity = SpecializationMapper.toEntity(dto);
        Specialization saved = repository.save(entity);
        return SpecializationMapper.toDto(saved);
    }

    public SpecializationResponseDto update(Long id, SpecializationRequestDto dto) {
        Specialization existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Specialization not found"));

        SpecializationMapper.updateEntity(existing, dto);

        Specialization updated = repository.save(existing);

        return SpecializationMapper.toDto(updated);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
