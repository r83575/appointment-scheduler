package com.example.demo.dto.roomspecialization;

import com.example.demo.model.Room;
import com.example.demo.model.Specialization;
import com.example.demo.validation.ExistsIdValid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RoomSpecializationRequestDto {

    @NotNull(message = "Room ID is required")
    @ExistsIdValid(entity = Room.class, message = "Room ID does not exist")
    private Long roomId;

    @NotNull(message = "Specialization ID is required")
    @ExistsIdValid(entity = Specialization.class, message = "Specialization ID does not exist")
    private Long specializationId;
}
