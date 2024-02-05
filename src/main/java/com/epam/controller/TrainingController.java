package com.epam.controller;

import com.epam.domain.Training;
import com.epam.dto.request.TrainingRequest;
import com.epam.service.TrainingService;
import com.epam.utils.validation.ValidModule;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/trainings")
public class TrainingController {
    private static final Logger logger = LogManager.getLogger(UserController.class);

    private final TrainingService trainingService;
    private final ValidModule validModule;

    @Autowired
    public TrainingController(TrainingService trainingService, ValidModule validModule) {
        this.trainingService = trainingService;
        this.validModule = validModule;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody TrainingRequest request) {
        try {
            validModule.trainingRequest(request);
            Training response = trainingService.save(request);
            logger.info("Training registered");
            return ResponseEntity.status(201).body(response);
        } catch (RuntimeException e) {
            logger.error("Training registration failed");
            return ResponseEntity.badRequest().body(e.getMessage());
            }
    }
}
