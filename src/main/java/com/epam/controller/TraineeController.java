package com.epam.controller;

import com.epam.dto.request.*;
import com.epam.dto.response.RegistrationResponse;
import com.epam.dto.response.UpdateTraineeResponse;
import com.epam.service.TraineeService;
import com.epam.utils.validation.ValidModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/profile")
    public ResponseEntity<?> getTraineeProfile(@RequestParam String username) {
        try {
            validModule.usernameValid(username);
            return ResponseEntity.ok().body(traineeService.findByUsername(username));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/readAll/trainings")
    public ResponseEntity<?> getAllTraineeTrainings(@RequestBody TraineeTrainingsRequest request) {
        try {
            validModule.traineeTrainings(request);
            return ResponseEntity.ok().body(traineeService.readTraineeTrainings(request));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateTrainee(@RequestBody UpdateTraineeRequest request) {
        try {
            validModule.updateTrainee(request);
            UpdateTraineeResponse response = traineeService.update(request);
            return ResponseEntity.ok().body(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteTrainee(@RequestParam String username) {
        try {
            validModule.usernameValid(username);
            traineeService.deleteByUsername(username);
            return ResponseEntity.ok().body("Trainee with username " + username + " deleted");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/updateStatus")
    public ResponseEntity<?> updateStatus(@RequestBody StatusRequest request) {
        try {
            validModule.updateStatus(request);
            traineeService.updateStatus(request);
            return ResponseEntity.ok().body("Trainee with username " + request.username() + " status updated");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
