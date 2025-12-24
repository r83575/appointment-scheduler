package com.example.demo.service;

import com.example.demo.dto.doctorspecialization.*;
import com.example.demo.exception.ConflictException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.DoctorSpecializationMapper;
import com.example.demo.model.DoctorSpecialization;
import com.example.demo.model.DoctorSpecializationId;
import com.example.demo.repository.DoctorSpecializationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
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
                .orElseThrow(() -> {
                    log.warn(
                            "DoctorSpecialization not found: doctorId={}, specializationId={}",
                            doctorId,
                            specializationId
                    );
                    return new ResourceNotFoundException("DoctorSpecialization not found");
                });

        return DoctorSpecializationMapper.toDto(entity);
    }

    public DoctorSpecializationResponseDto create(DoctorSpecializationRequestDto dto) {

        log.info(
                "Creating DoctorSpecialization: doctorId={}, specializationId={}",
                dto.getDoctorId(),
                dto.getSpecializationId()
        );

        DoctorSpecializationId id =
                new DoctorSpecializationId(dto.getDoctorId(), dto.getSpecializationId());

        if (repository.existsById(id)) {
            log.warn(
                    "DoctorSpecialization already exists: doctorId={}, specializationId={}",
                    dto.getDoctorId(),
                    dto.getSpecializationId()
            );
            throw new ConflictException("This doctor is already assigned to this specialization");
        }

        DoctorSpecialization entity = DoctorSpecializationMapper.toEntity(dto);
        DoctorSpecialization saved = repository.save(entity);

        log.info(
                "DoctorSpecialization created successfully: doctorId={}, specializationId={}",
                saved.getDoctor().getId(),
                saved.getSpecialization().getId()
        );

        return DoctorSpecializationMapper.toDto(saved);
    }

    public DoctorSpecializationResponseDto update(
            Long doctorId,
            Long specializationId,
            DoctorSpecializationRequestDto dto
    ) {

        log.info(
                "Updating DoctorSpecialization: doctorId={}, specializationId={}",
                doctorId,
                specializationId
        );

        DoctorSpecializationId id = new DoctorSpecializationId(doctorId, specializationId);

        DoctorSpecialization existing = repository.findById(id)
                .orElseThrow(() -> {
                    log.warn(
                            "DoctorSpecialization not found for update: doctorId={}, specializationId={}",
                            doctorId,
                            specializationId
                    );
                    return new ResourceNotFoundException("DoctorSpecialization not found");
                });

        DoctorSpecializationId newId =
                new DoctorSpecializationId(dto.getDoctorId(), dto.getSpecializationId());

        if (!newId.equals(id) && repository.existsById(newId)) {
            log.warn(
                    "DoctorSpecialization conflict on update: doctorId={}, specializationId={}",
                    dto.getDoctorId(),
                    dto.getSpecializationId()
            );
            throw new ConflictException("This doctor is already assigned to this specialization");
        }

        DoctorSpecializationMapper.updateEntity(existing, dto);

        DoctorSpecialization updated = repository.save(existing);

        log.info(
                "DoctorSpecialization updated successfully: doctorId={}, specializationId={}",
                updated.getDoctor().getId(),
                updated.getSpecialization().getId()
        );

        return DoctorSpecializationMapper.toDto(updated);
    }

    public void delete(Long doctorId, Long specializationId) {

        log.info(
                "Deleting DoctorSpecialization: doctorId={}, specializationId={}",
                doctorId,
                specializationId
        );

        DoctorSpecializationId id = new DoctorSpecializationId(doctorId, specializationId);
        repository.deleteById(id);
    }
}
