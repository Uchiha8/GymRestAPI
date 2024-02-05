package com.epam.controller;

import com.epam.domain.TrainingType;
import com.epam.service.TrainingTypeService;
import com.epam.utils.validation.ValidModule;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trainingTypes")
public class TrainingTypeController {
    private static final Logger logger = LogManager.getLogger(UserController.class);

    private final TrainingTypeService trainingTypeService;
    private final ValidModule validModule;

    @Autowired
    public TrainingTypeController(TrainingTypeService trainingTypeService, ValidModule validModule) {
        this.trainingTypeService = trainingTypeService;
        this.validModule = validModule;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody TrainingType request) {
        try {
            validModule.trainingTypeValid(request);
            TrainingType response = trainingTypeService.save(request);
            logger.info("Training type registered");
            return ResponseEntity.status(201).body(response);
        } catch (IllegalArgumentException e) {
            logger.error("Training type registration failed");
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll() {
        try {
            logger.info("All training types found");
            return ResponseEntity.ok().body(trainingTypeService.findAll());
        } catch (RuntimeException e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
