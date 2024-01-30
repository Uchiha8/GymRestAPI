package com.epam.controller;

import com.epam.dto.request.TraineeRegistrationRequest;
import com.epam.dto.response.RegistrationResponse;
import com.epam.service.TraineeService;
import com.epam.utils.validation.ValidModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/trainees")
public class TraineeController {
    private final TraineeService traineeService;
    private final ValidModule validModule;

    @Autowired
    public TraineeController(TraineeService traineeService, ValidModule validModule) {
        this.traineeService = traineeService;
        this.validModule = validModule;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody TraineeRegistrationRequest request) {
        try {
            validModule.traineeRegistration(request);
            RegistrationResponse response = traineeService.save(request);
            return ResponseEntity.status(201).body(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
