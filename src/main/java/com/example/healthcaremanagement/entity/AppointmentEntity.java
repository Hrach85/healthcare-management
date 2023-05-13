package com.example.healthcaremanagement.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "appointment")
public class AppointmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private PatientEntity patientId;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private DoctorEntity doctorId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
