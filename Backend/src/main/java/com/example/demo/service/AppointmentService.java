package com.example.demo.service;

import com.example.demo.dto.appointment.AppointmentRequestDto;
import com.example.demo.dto.appointment.AppointmentResponseDto;
import com.example.demo.dto.page.PageResponseDto;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.AppointmentMapper;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;

@Slf4j
@Service
@Transactional
public class AppointmentService {

    private final AppointmentRepository appointmentRepo;
    private final DoctorRepository doctorRepo;
    private final CustomerRepository customerRepo;
    private final RoomRepository roomRepo;
    private final SpecializationRepository specializationRepo;

    public AppointmentService(
            AppointmentRepository appointmentRepo,
            DoctorRepository doctorRepo,
            CustomerRepository customerRepo,
            RoomRepository roomRepo,
            SpecializationRepository specializationRepo
    ) {
        this.appointmentRepo = appointmentRepo;
        this.doctorRepo = doctorRepo;
        this.customerRepo = customerRepo;
        this.roomRepo = roomRepo;
        this.specializationRepo = specializationRepo;
    }

    // ===== Pagination =====
    public PageResponseDto<AppointmentResponseDto> getAll(Pageable pageable) {

        log.info(
                "Fetching appointments page: page={}, size={}, sort={}",
                pageable.getPageNumber(),
                pageable.getPageSize(),
                pageable.getSort()
        );

        Page<Appointment> page = appointmentRepo.findAll(pageable);

        return new PageResponseDto<>(
                page.getContent()
                        .stream()
                        .map(AppointmentMapper::toDto)
                        .toList(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages()
        );
    }

    // ===== CRUD =====
    public AppointmentResponseDto create(AppointmentRequestDto dto) {

        log.info(
                "Creating appointment: doctorId={}, customerId={}, roomId={}, specializationId={}, date={}, startTime={}",
                dto.getDoctorId(),
                dto.getCustomerId(),
                dto.getRoomId(),
                dto.getSpecializationId(),
                dto.getDate(),
                dto.getStartTime()
        );

        Doctor doctor = doctorRepo.findById(dto.getDoctorId())
                .orElseThrow(() -> {
                    log.warn("Doctor not found: id={}", dto.getDoctorId());
                    return new ResourceNotFoundException("Doctor not found");
                });

        Customer customer = customerRepo.findById(dto.getCustomerId())
                .orElseThrow(() -> {
                    log.warn("Customer not found: id={}", dto.getCustomerId());
                    return new ResourceNotFoundException("Customer not found");
                });

        Room room = roomRepo.findById(dto.getRoomId())
                .orElseThrow(() -> {
                    log.warn("Room not found: id={}", dto.getRoomId());
                    return new ResourceNotFoundException("Room not found");
                });

        Specialization specialization = specializationRepo.findById(dto.getSpecializationId())
                .orElseThrow(() -> {
                    log.warn("Specialization not found: id={}", dto.getSpecializationId());
                    return new ResourceNotFoundException("Specialization not found");
                });

        int duration = specialization.getDefaultDuration();
        LocalTime calculatedEndTime = dto.getStartTime().plusMinutes(duration);

        Appointment entity =
                AppointmentMapper.toEntity(dto, doctor, customer, room, specialization, calculatedEndTime);

        Appointment saved = appointmentRepo.save(entity);

        log.info("Appointment created successfully: appointmentId={}", saved.getId());

        return AppointmentMapper.toDto(saved);
    }

    public AppointmentResponseDto getById(Long id) {
        Appointment entity = appointmentRepo.findById(id)
                .orElseThrow(() -> {
                    log.warn("Appointment not found: id={}", id);
                    return new ResourceNotFoundException("Appointment not found");
                });

        return AppointmentMapper.toDto(entity);
    }

    public AppointmentResponseDto update(Long id, AppointmentRequestDto dto) {

        log.info("Updating appointment: id={}", id);

        Appointment existing = appointmentRepo.findById(id)
                .orElseThrow(() -> {
                    log.warn("Appointment not found: id={}", id);
                    return new ResourceNotFoundException("Appointment not found");
                });

        Doctor doctor = doctorRepo.findById(dto.getDoctorId())
                .orElseThrow(() -> {
                    log.warn("Doctor not found: id={}", dto.getDoctorId());
                    return new ResourceNotFoundException("Doctor not found");
                });

        Customer customer = customerRepo.findById(dto.getCustomerId())
                .orElseThrow(() -> {
                    log.warn("Customer not found: id={}", dto.getCustomerId());
                    return new ResourceNotFoundException("Customer not found");
                });

        Room room = roomRepo.findById(dto.getRoomId())
                .orElseThrow(() -> {
                    log.warn("Room not found: id={}", dto.getRoomId());
                    return new ResourceNotFoundException("Room not found");
                });

        Specialization specialization = specializationRepo.findById(dto.getSpecializationId())
                .orElseThrow(() -> {
                    log.warn("Specialization not found: id={}", dto.getSpecializationId());
                    return new ResourceNotFoundException("Specialization not found");
                });

        existing.setDoctor(doctor);
        existing.setCustomer(customer);
        existing.setRoom(room);
        existing.setSpecialization(specialization);
        existing.setDate(dto.getDate());
        existing.setStartTime(dto.getStartTime());

        int duration = specialization.getDefaultDuration();
        existing.setEndTime(dto.getStartTime().plusMinutes(duration));

        Appointment updated = appointmentRepo.save(existing);

        log.info("Appointment updated successfully: appointmentId={}", updated.getId());

        return AppointmentMapper.toDto(updated);
    }

    public void delete(Long id) {
        log.info("Deleting appointment: id={}", id);
        appointmentRepo.deleteById(id);
    }
}
