package com.example.demo.service;

import com.example.demo.dto.doctor.DoctorRequestDto;
import com.example.demo.dto.doctor.DoctorResponseDto;
import com.example.demo.mapper.DoctorMapper;
import com.example.demo.model.Doctor;
import com.example.demo.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        return DoctorMapper.toDto(entity);
    }

    public DoctorResponseDto create(DoctorRequestDto dto) {
        Doctor entity = DoctorMapper.toEntity(dto);
        Doctor saved = repository.save(entity);
        return DoctorMapper.toDto(saved);
    }

    public DoctorResponseDto update(Long id, DoctorRequestDto dto) {
        Doctor entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());

        Doctor saved = repository.save(entity);
        return DoctorMapper.toDto(saved);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
