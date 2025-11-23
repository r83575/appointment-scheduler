package com.example.demo.model;

import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorSpecializationId implements Serializable {

    private Long doctorId;
    private Long specializationId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DoctorSpecializationId that)) return false;
        return Objects.equals(doctorId, that.doctorId) &&
                Objects.equals(specializationId, that.specializationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(doctorId, specializationId);
    }
}
