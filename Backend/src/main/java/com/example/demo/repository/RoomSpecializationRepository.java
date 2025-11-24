package com.example.demo.repository;


import com.example.demo.model.RoomSpecialization;
import com.example.demo.model.RoomSpecializationId;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomSpecializationRepository extends JpaRepository<RoomSpecialization, RoomSpecializationId> {
}
