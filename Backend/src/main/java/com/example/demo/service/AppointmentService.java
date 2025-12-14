package com.example.demo.service;

import com.example.demo.dto.appointment.AppointmentRequestDto;
import com.example.demo.dto.appointment.AppointmentResponseDto;
import com.example.demo.mapper.AppointmentMapper;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.List;

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

    public AppointmentResponseDto create(AppointmentRequestDto dto) {

        Doctor doctor = doctorRepo.findById(dto.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        Customer customer = customerRepo.findById(dto.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Room room = roomRepo.findById(dto.getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found"));

        Specialization specialization = specializationRepo.findById(dto.getSpecializationId())
                .orElseThrow(() -> new RuntimeException("Specialization not found"));

        int duration = specialization.getDefaultDuration();
        var calculatedEndTime = dto.getStartTime().plusMinutes(duration);

        Appointment entity = AppointmentMapper.toEntity(dto, doctor, customer, room, specialization, calculatedEndTime);

        Appointment saved = appointmentRepo.save(entity);

        return AppointmentMapper.toDto(saved);
    }


    public List<AppointmentResponseDto> getAll() {
        return appointmentRepo.findAll()
                .stream()
                .map(AppointmentMapper::toDto)
                .toList();
    }

    public AppointmentResponseDto getById(Long id) {
        Appointment entity = appointmentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        return AppointmentMapper.toDto(entity);
    }

    public AppointmentResponseDto update(Long id, AppointmentRequestDto dto) {

        Appointment existing = appointmentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        Doctor doctor = doctorRepo.findById(dto.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        Customer customer = customerRepo.findById(dto.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Room room = roomRepo.findById(dto.getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found"));

        Specialization specialization = specializationRepo.findById(dto.getSpecializationId())
                .orElseThrow(() -> new RuntimeException("Specialization not found"));

        existing.setDoctor(doctor);
        existing.setCustomer(customer);
        existing.setRoom(room);
        existing.setSpecialization(specialization);
        existing.setDate(dto.getDate());
        existing.setStartTime(dto.getStartTime());
        int duration = specialization.getDefaultDuration();
        LocalTime calculatedEndTime = dto.getStartTime().plusMinutes(duration);
        existing.setEndTime(calculatedEndTime);

        Appointment updated = appointmentRepo.save(existing);

        return AppointmentMapper.toDto(updated);
    }

    public void delete(Long id) {
        appointmentRepo.deleteById(id);
    }
}
