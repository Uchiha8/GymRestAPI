package com.epam.controller;

import com.epam.dto.request.*;
import com.epam.dto.response.RegistrationResponse;
import com.epam.dto.response.UpdateTraineeResponse;
import com.epam.service.TraineeService;
import com.epam.utils.validation.ValidModule;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trainees")
public class TraineeController {
    private static final Logger logger = LogManager.getLogger(UserController.class);

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
            logger.info("Trainee registered");
            return ResponseEntity.status(201).body(response);
        } catch (IllegalArgumentException e) {
            logger.error("Trainee registration failed");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getTraineeProfile(@RequestParam String username) {
        try {
            validModule.usernameValid(username);
            logger.info("Trainee profile found");
            return ResponseEntity.ok().body(traineeService.findByUsername(username));
        } catch (RuntimeException e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/readAll/trainings")
    public ResponseEntity<?> getAllTraineeTrainings(@RequestBody TraineeTrainingsRequest request) {
        try {
            validModule.traineeTrainings(request);
            logger.info("Trainee trainings found");
            return ResponseEntity.ok().body(traineeService.readTraineeTrainings(request));
        } catch (RuntimeException e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateTrainee(@RequestBody UpdateTraineeRequest request) {
        try {
            validModule.updateTrainee(request);
            UpdateTraineeResponse response = traineeService.update(request);
            logger.info("Trainee updated");
            return ResponseEntity.ok().body(response);
        } catch (RuntimeException e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteTrainee(@RequestParam String username) {
        try {
            validModule.usernameValid(username);
            traineeService.deleteByUsername(username);
            logger.info("Trainee deleted");
            return ResponseEntity.ok().body("Trainee with username " + username + " deleted");
        } catch (RuntimeException e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/updateStatus")
    public ResponseEntity<?> updateStatus(@RequestBody StatusRequest request) {
        try {
            validModule.updateStatus(request);
            traineeService.updateStatus(request);
            logger.info("Trainee status updated");
            return ResponseEntity.ok().body("Trainee with username " + request.username() + " status updated");
        } catch (RuntimeException e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
