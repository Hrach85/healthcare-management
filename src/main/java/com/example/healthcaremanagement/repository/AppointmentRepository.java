package com.example.healthcaremanagement.repository;

import com.example.healthcaremanagement.entity.AppointmentEntity;
import com.example.healthcaremanagement.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Integer> {
}
