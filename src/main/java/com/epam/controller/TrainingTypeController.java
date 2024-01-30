package com.epam.controller;

import com.epam.domain.TrainingType;
import com.epam.service.TrainingTypeService;
import com.epam.utils.validation.ValidModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trainingTypes")
public class TrainingTypeController {
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
            return ResponseEntity.status(201).body(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll() {
        try {
            return ResponseEntity.ok().body(trainingTypeService.findAll());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
