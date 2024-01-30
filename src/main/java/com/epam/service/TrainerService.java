package com.epam.service;

import com.epam.domain.Trainer;
import com.epam.domain.Training;
import com.epam.domain.TrainingType;
import com.epam.domain.User;
import com.epam.dto.request.TrainerRegistrationRequest;
import com.epam.dto.request.UpdateTrainerRequest;
import com.epam.dto.response.*;
import com.epam.repository.TrainerRepository;
import com.epam.utils.exception.TrainerNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public TrainerProfileResponse findByUsername(String username) {
        if (!trainerRepository.existsByUsername(username)) {
            throw new RuntimeException("Trainer with username " + username + " not found");
        }
        Trainer trainer = trainerRepository.findByUsername(username);
        List<TraineeList> traineesLists = trainer.getTrainings().stream()
                .map(Training::getTrainee)
                .map(trainee -> new TraineeList(
                        trainee.getUser().getUsername(),
                        trainee.getUser().getFirstName(),
                        trainee.getUser().getLastName()))
                .toList();
        return new TrainerProfileResponse(
                trainer.getUser().getFirstName(),
                trainer.getUser().getLastName(),
                trainer.getTrainingType(),
                trainer.getUser().getActive(),
                traineesLists
        );
    }

    public UpdateTrainerResponse update(UpdateTrainerRequest request) {
        if (!trainerRepository.existsByUsername(request.username())) {
            throw new TrainerNotFoundException("Trainer with username " + request.username() + " not found");
        }
        User user = userService.findByUsername(request.username());
        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setActive(request.isActive());
        User resultUser = userService.update(user);
        Trainer trainer = trainerRepository.findByUsername(request.username());
        trainer.setUser(resultUser);
        if (request.trainingType() != null) {
            TrainingType trainingType = trainingTypeService.findByName(request.trainingType().getName());
            trainer.setTrainingType(trainingType);
        }
        Trainer resultTrainer = trainerRepository.update(trainer);
        List<TraineeList> traineesLists = resultTrainer.getTrainings().stream()
                .map(Training::getTrainee)
                .map(trainee -> new TraineeList(
                        trainer.getUser().getUsername(),
                        trainer.getUser().getFirstName(),
                        trainer.getUser().getLastName()))
                .toList();
        return new UpdateTrainerResponse(
                resultTrainer.getUser().getUsername(),
                resultTrainer.getUser().getFirstName(),
                resultTrainer.getUser().getLastName(),
                resultTrainer.getTrainingType(),
                resultTrainer.getUser().getActive(),
                traineesLists
        );
    }

    public void deleteByUsername(String username) {
        if (!trainerRepository.existsByUsername(username)) {
            throw new RuntimeException("Trainer with username " + username + " not found");
        }
        Trainer trainer = trainerRepository.findByUsername(username);
        trainerRepository.delete(trainer);
    }
}
