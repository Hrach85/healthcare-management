package com.example.healthcaremanagement.controller;

import com.example.healthcaremanagement.entity.AppointmentEntity;
import com.example.healthcaremanagement.entity.DoctorEntity;
import com.example.healthcaremanagement.entity.PatientEntity;
import com.example.healthcaremanagement.repository.AppointmentRepository;
import com.example.healthcaremanagement.repository.DoctorRepository;
import com.example.healthcaremanagement.repository.PatientRepository;
import com.example.healthcaremanagement.security.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;

    @GetMapping()
    private String AppointmentPage(ModelMap modelMap) {
        List<AppointmentEntity> appointmentList = appointmentRepository.findAll();
        modelMap.addAttribute("appointments", appointmentList);
        return "appointments";
    }

    @GetMapping("/add")
    private String appointmentsAddPage(ModelMap modelMap) {
        List<PatientEntity> patientList = patientRepository.findAll();
        List<DoctorEntity> doctorList = doctorRepository.findAll();
        modelMap.addAttribute("patients", patientList);
        modelMap.addAttribute("doctors", doctorList);
        return "appointmentsAdd";
    }

    @PostMapping("/add")
    private String appointmentsAdd(@ModelAttribute AppointmentEntity appointment,
                                   @RequestParam("dateTime")
                                   @DateTimeFormat(pattern = "yyyy-MM-dd''HH:mm") LocalDateTime dateTime,
                                   @AuthenticationPrincipal CurrentUser currentUser) {
        appointment.setDateTime(dateTime);
        appointment.setUser(currentUser.getUser());
        appointmentRepository.save(appointment);
        return "redirect:/appointments";
    }

    @GetMapping("/remove")
    public String RemoveDoctor(@RequestParam("id") int id) {
        appointmentRepository.deleteById(id);
        return "redirect:/appointments";
    }
}
