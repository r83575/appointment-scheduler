package com.example.demo.repository;

import com.example.demo.model.DoctorSpecialization;
import com.example.demo.model.DoctorSpecializationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorSpecializationRepository extends JpaRepository<DoctorSpecialization, DoctorSpecializationId> {
}
