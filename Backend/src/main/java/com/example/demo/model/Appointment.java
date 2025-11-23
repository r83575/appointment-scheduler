package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import com.example.demo.model.enums.AppointmentStatus;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "specialization_id")
    private Specialization specialization;

    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;
}
