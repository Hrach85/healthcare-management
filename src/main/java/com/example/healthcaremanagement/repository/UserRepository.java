package com.example.healthcaremanagement.repository;

import com.example.healthcaremanagement.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {


    Optional<UserEntity> findByEmail(String email);
}
