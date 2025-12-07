package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(DoctorSpecializationId.class)
public class DoctorSpecialization {

    @Id
    @Column(name = "doctor_id")
    private Long doctorId;

    @Id
    @Column(name = "specialization_id")
    private Long specializationId;

    @ManyToOne
    @JoinColumn(name = "doctor_id", insertable = false, updatable = false)
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "specialization_id", insertable = false, updatable = false)
    private Specialization specialization;
}
