package com.example.demo.mapper;

import com.example.demo.dto.appointment.AppointmentRequestDto;
import com.example.demo.dto.appointment.AppointmentResponseDto;
import com.example.demo.model.*;
import com.example.demo.model.enums.AppointmentStatus;

public class AppointmentMapper {

    public static Appointment toEntity(
            AppointmentRequestDto dto,
            Doctor doctor,
            Customer customer,
            Room room,
            Specialization specialization
    ) {
        return Appointment.builder()
                .doctor(doctor)
                .customer(customer)
                .room(room)
                .specialization(specialization)
                .date(dto.getDate())
                .startTime(dto.getStartTime())
                .endTime(dto.getEndTime())
                .status(AppointmentStatus.CANCELLED)
                .build();
    }

    public static AppointmentResponseDto toDto(Appointment entity) {
        AppointmentResponseDto dto = new AppointmentResponseDto();
        dto.setId(entity.getId());
        dto.setDoctorId(entity.getDoctor().getId());
        dto.setCustomerId(entity.getCustomer().getId());
        dto.setRoomId(entity.getRoom().getId());
        dto.setSpecializationId(entity.getSpecialization().getId());
        dto.setDate(entity.getDate());
        dto.setStartTime(entity.getStartTime());
        dto.setEndTime(entity.getEndTime());
        dto.setStatus(entity.getStatus());
        return dto;
    }
}
