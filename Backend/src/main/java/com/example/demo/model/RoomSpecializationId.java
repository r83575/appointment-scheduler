package com.example.demo.model;

import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomSpecializationId implements Serializable {

    private Long roomId;
    private Long specializationId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoomSpecializationId that)) return false;
        return Objects.equals(roomId, that.roomId) &&
                Objects.equals(specializationId, that.specializationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomId, specializationId);
    }
}
