package com.epam.service;

import com.epam.domain.Trainer;
import com.epam.domain.TrainingType;
import com.epam.domain.User;
import com.epam.dto.request.TrainerRegistrationRequest;
import com.epam.dto.response.RegistrationResponse;
import com.epam.repository.TrainerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class TrainerService {
    private final TrainerRepository trainerRepository;
    private final TrainingTypeService trainingTypeService;
    private final UserService userService;

    @Autowired
    public TrainerService(TrainerRepository trainerRepository, TrainingTypeService trainingTypeService, UserService userService) {
        this.trainerRepository = trainerRepository;
        this.trainingTypeService = trainingTypeService;
        this.userService = userService;
    }

    public RegistrationResponse save(TrainerRegistrationRequest request) {
        // mapping from dto to module
        User user = new User(request.firstName(), request.lastName(), null, null, false);
        User userResult = userService.save(user);
        TrainingType trainingType = trainingTypeService.findByName(request.trainingType().getName());
        Trainer trainer = new Trainer(trainingType, userResult, List.of());
        Trainer result = trainerRepository.save(trainer);
        return new RegistrationResponse(result.getUser().getUsername(), result.getUser().getPassword());
    }
}
