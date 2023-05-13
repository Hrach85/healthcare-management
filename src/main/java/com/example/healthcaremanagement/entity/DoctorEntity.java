package com.example.healthcaremanagement.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "doctor")
@Entity
public class DoctorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String surname;
    private String email;
    private String specialty;
    private String phone_number;
    private String profile_pic;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}

