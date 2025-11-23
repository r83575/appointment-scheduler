package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Specialization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private int defaultDuration;

    @OneToMany(mappedBy = "specialization", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DoctorSpecialization> doctors;

    @OneToMany(mappedBy = "specialization", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RoomSpecialization> rooms;

    @OneToMany(mappedBy = "specialization", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Appointment> appointments;

}
