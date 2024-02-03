package com.epam.controller;

import com.epam.dto.request.StatusRequest;
import com.epam.dto.request.TrainerRegistrationRequest;
import com.epam.dto.request.TrainerTrainingsRequest;
import com.epam.dto.request.UpdateTrainerRequest;
import com.epam.dto.response.RegistrationResponse;
import com.epam.dto.response.UpdateTrainerResponse;
import com.epam.service.TrainerService;
import com.epam.utils.validation.ValidModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/profile")
    public ResponseEntity<?> profile(@RequestParam String username) {
        try {
            validModule.usernameValid(username);
            return ResponseEntity.ok(trainerService.findByUsername(username));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody UpdateTrainerRequest request) {
        try {
            validModule.updateTrainer(request);
            UpdateTrainerResponse response = trainerService.update(request);
            return ResponseEntity.status(201).body(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/active/trainers")
    public ResponseEntity<?> getActiveTrainers(@RequestParam String username) {
        try {
            validModule.usernameValid(username);
            return ResponseEntity.ok(trainerService.activeTrainers(username));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("readAll/trainings")
    public ResponseEntity<?> getAllTrainerTrainings(@RequestBody TrainerTrainingsRequest request) {
        try {
            validModule.trainerTrainings(request);
            return ResponseEntity.ok(trainerService.readTrainerTrainings(request));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/updateStatus")
    public ResponseEntity<?> updateStatus(@RequestBody StatusRequest request) {
        try {
            validModule.updateStatus(request);
            trainerService.updateStatus(request);
            return ResponseEntity.ok().body("Trainer with username " + request.username() + " status updated");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
