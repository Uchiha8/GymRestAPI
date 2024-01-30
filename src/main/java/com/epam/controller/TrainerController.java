package com.epam.controller;

import com.epam.dto.request.TrainerRegistrationRequest;
import com.epam.dto.response.RegistrationResponse;
import com.epam.service.TrainerService;
import com.epam.utils.validation.ValidModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/trainers")
public class TrainerController {
    private final TrainerService trainerService;
    private final ValidModule validModule;

    @Autowired
    public TrainerController(TrainerService trainerService, ValidModule validModule) {
        this.trainerService = trainerService;
        this.validModule = validModule;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody TrainerRegistrationRequest request) {
        try {
            validModule.trainerRegistration(request);
            RegistrationResponse response = trainerService.save(request);
            return ResponseEntity.status(201).body(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
