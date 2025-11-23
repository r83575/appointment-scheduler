package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DoctorSpecialization> specializations;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DoctorAvailability> availability;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Appointment> appointments;

}
