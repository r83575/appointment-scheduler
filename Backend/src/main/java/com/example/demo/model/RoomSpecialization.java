package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(RoomSpecializationId.class)
public class RoomSpecialization {

    @Id
    @Column(name = "room_id")
    private Long roomId;

    @Id
    @Column(name = "specialization_id")
    private Long specializationId;

    @ManyToOne
    @MapsId("roomId")
    @JoinColumn(name = "room_id", insertable = false, updatable = false)
    private Room  room;

    @ManyToOne
    @MapsId("specializationId")
    @JoinColumn(name = "specialization_id", insertable = false, updatable = false)
    private Specialization specialization;
}
