package com.example.healthcaremanagement.controller;

import com.example.healthcaremanagement.entity.DoctorEntity;
import com.example.healthcaremanagement.repository.DoctorRepository;
import com.example.healthcaremanagement.security.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    @Value("${doctoring.upload.image.path}")
    private String imageUploadPath;

    @GetMapping()
    public String DoctorPage(ModelMap modelMap) {
        List<DoctorEntity> all = doctorRepository.findAll();
        modelMap.addAttribute("doctors", all);
        return "doctors";
    }

    @GetMapping("/remove")
    public String RemoveDoctor(@RequestParam("id") int id) {
        doctorRepository.deleteById(id);
        return "redirect:/doctors";
    }

    @GetMapping("/add")
    public String AddDoctorPage() {
        return "doctorsAdd";
    }

    @PostMapping("/add")
    public String doctorsAdd(@ModelAttribute DoctorEntity doctor,
                             @RequestParam("image") MultipartFile multipartFile,
                             @AuthenticationPrincipal CurrentUser currentUser) throws IOException {
        if (multipartFile != null && !multipartFile.isEmpty()) {
            String fileName = System.nanoTime() + "_" + multipartFile.getOriginalFilename();
            File file = new File(imageUploadPath + fileName);
            multipartFile.transferTo(file);
            doctor.setProfile_pic(fileName);
        }
        doctor.setUser(currentUser.getUser());
        doctorRepository.save(doctor);
        return "redirect:/doctors";
    }
}