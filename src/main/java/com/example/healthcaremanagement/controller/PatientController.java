package com.example.healthcaremanagement.controller;

import com.example.healthcaremanagement.entity.PatientEntity;
import com.example.healthcaremanagement.repository.PatientRepository;
import com.example.healthcaremanagement.security.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("patients")
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @GetMapping()
    public String PatientPage(ModelMap modelMap) {
        List<PatientEntity> all = patientRepository.findAll();
        modelMap.addAttribute("patients", all);
        return "patients";
    }

    @GetMapping("/add")
    public String PatientAddPage() {
        return "patientsadd";
    }

    @PostMapping("/add")
    public String patientsAdd(@ModelAttribute PatientEntity patient,
                              @AuthenticationPrincipal CurrentUser currentUser) {
       patient.setUser(currentUser.getUser());
        patientRepository.save(patient);
        return "redirect:/patients";
    }

    @GetMapping("/remove")
    public String RemovePatient(@RequestParam("id") int id) {
        patientRepository.deleteById(id);
        return "redirect:/patients";
    }
}