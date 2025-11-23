package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int roomNumber;
    private String location;
    private boolean isActive;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RoomSpecialization> specializations;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Appointment> appointments;

}
