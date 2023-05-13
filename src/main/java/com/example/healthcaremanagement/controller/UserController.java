package com.example.healthcaremanagement.controller;

import com.example.healthcaremanagement.entity.UserEntity;
import com.example.healthcaremanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UserEntity user) {
        Optional<UserEntity> userEmailDB = userRepository.findByEmail(user.getEmail());
        if (userEmailDB.isEmpty()) {
            String password = user.getPassword();
            String encodePassword = passwordEncoder.encode(password);
            user.setPassword(encodePassword);
            userRepository.save(user);
        }
        return "redirect:/";
    }
}
