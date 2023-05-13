package com.example.healthcaremanagement.repository;


import com.example.healthcaremanagement.entity.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository  extends JpaRepository<DoctorEntity,Integer> {
}
