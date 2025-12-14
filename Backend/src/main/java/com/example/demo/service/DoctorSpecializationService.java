package com.example.demo.service;

import com.example.demo.dto.doctorspecialization.*;
import com.example.demo.mapper.DoctorSpecializationMapper;
import com.example.demo.model.DoctorSpecialization;
import com.example.demo.model.DoctorSpecializationId;
import com.example.demo.repository.DoctorSpecializationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class DoctorSpecializationService {

    private final DoctorSpecializationRepository repository;

    public List<DoctorSpecializationResponseDto> getAll() {
        return repository.findAll()
                .stream()
                .map(DoctorSpecializationMapper::toDto)
                .toList();
    }

    public DoctorSpecializationResponseDto getById(Long doctorId, Long specializationId) {
        DoctorSpecializationId id = new DoctorSpecializationId(doctorId, specializationId);

        DoctorSpecialization entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("DoctorSpecialization not found"));

        return DoctorSpecializationMapper.toDto(entity);
    }

    public DoctorSpecializationResponseDto create(DoctorSpecializationRequestDto dto) {

        DoctorSpecializationId id =
                new DoctorSpecializationId(dto.getDoctorId(), dto.getSpecializationId());

        if (repository.existsById(id)) {
            throw new RuntimeException("This doctor is already assigned to this specialization");
        }

        DoctorSpecialization entity = DoctorSpecializationMapper.toEntity(dto);
        DoctorSpecialization saved = repository.save(entity);

        return DoctorSpecializationMapper.toDto(saved);
    }

    public DoctorSpecializationResponseDto update(Long doctorId, Long specializationId,
                                                  DoctorSpecializationRequestDto dto) {

        DoctorSpecializationId id = new DoctorSpecializationId(doctorId, specializationId);

        DoctorSpecialization existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("DoctorSpecialization not found"));

        DoctorSpecializationId newId =
                new DoctorSpecializationId(dto.getDoctorId(), dto.getSpecializationId());

        if (!newId.equals(id) && repository.existsById(newId)) {
            throw new RuntimeException("This doctor is already assigned to this specialization");
        }

        DoctorSpecializationMapper.updateEntity(existing, dto);

        DoctorSpecialization updated = repository.save(existing);

        return DoctorSpecializationMapper.toDto(updated);
    }


    public void delete(Long doctorId, Long specializationId) {
        DoctorSpecializationId id = new DoctorSpecializationId(doctorId, specializationId);
        repository.deleteById(id);
    }
}
