package com.epam.service;

import com.epam.domain.*;
import com.epam.dto.request.*;
import com.epam.dto.response.*;
import com.epam.repository.TraineeRepository;
import com.epam.repository.TrainingTypeRepository;
import com.epam.utils.exception.TraineeNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TraineeService {
    private final TraineeRepository traineeRepository;
    private final UserService userService;

    private final TrainingTypeRepository trainingTypeRepository;


    @Autowired
    public TraineeService(TraineeRepository traineeRepository, UserService userService, TrainingTypeRepository trainingTypeRepository) {
        this.traineeRepository = traineeRepository;
        this.userService = userService;
        this.trainingTypeRepository = trainingTypeRepository;
    }

    public RegistrationResponse save(TraineeRegistrationRequest request) {
        // mapping from dto to module
        User user = new User(request.firstName(), request.lastName(), null, null, false);
        User userResult = userService.save(user);
        Trainee trainee = new Trainee(request.dateOfBirth(), request.address(), userResult, List.of());
        Trainee result = traineeRepository.save(trainee);
        return new RegistrationResponse(result.getUser().getUsername(), result.getUser().getPassword());
    }

    public TraineeProfileResponse findByUsername(String username) {
        if (!traineeRepository.existsByUsername(username)) {
            throw new RuntimeException("Trainee with username " + username + " not found");
        }
        Trainee trainee = traineeRepository.findByUsername(username);
        List<TrainersList> trainersLists = trainee.getTrainings().stream()
                .map(Training::getTrainer)
                .map(trainer -> new TrainersList(
                        trainer.getUser().getUsername(),
                        trainer.getUser().getFirstName(),
                        trainer.getUser().getLastName(),
                        trainer.getTrainingType()
                ))
                .collect(Collectors.toList());
        return new TraineeProfileResponse(
                trainee.getUser().getFirstName(),
                trainee.getUser().getLastName(),
                trainee.getDateOfBirth(),
                trainee.getAddress(),
                trainee.getUser().getActive(),
                trainersLists
        );
    }

    public UpdateTraineeResponse update(UpdateTraineeRequest request) {
        if (!traineeRepository.existsByUsername(request.username())) {
            throw new TraineeNotFoundException("Trainee with username " + request.username() + " not found");
        }
        User user = userService.findByUsername(request.username());
        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setActive(request.isActive());
        User userResult = userService.update(user);
        Trainee result = traineeRepository.findByUsername(request.username());
        result.setUser(userResult);
        if (request.address() != null) {
            result.setAddress(request.address());
        }
        if (request.dateOfBirth() != null) {
            result.setDateOfBirth(request.dateOfBirth());
        }
        Trainee traineeResult = traineeRepository.update(result);
        List<TrainersList> trainersLists = traineeResult.getTrainings().stream()
                .map(Training::getTrainer)
                .map(trainer -> new TrainersList(
                        trainer.getUser().getUsername(),
                        trainer.getUser().getFirstName(),
                        trainer.getUser().getLastName(),
                        trainer.getTrainingType()
                ))
                .collect(Collectors.toList());
        return new UpdateTraineeResponse(
                result.getUser().getUsername(),
                result.getUser().getFirstName(),
                result.getUser().getLastName(),
                result.getDateOfBirth(),
                result.getAddress(),
                result.getUser().getActive(),
                trainersLists
        );
    }

    public void deleteByUsername(String username) {
        if (!traineeRepository.existsByUsername(username)) {
            throw new TraineeNotFoundException("Trainee with username " + username + " not found");
        }
        Trainee trainee = traineeRepository.findByUsername(username);
        traineeRepository.delete(trainee);
    }

    public void updateStatus(StatusRequest request) {
        if (!traineeRepository.existsByUsername(request.username())) {
            throw new TraineeNotFoundException("Trainee with username " + request.username() + " not found");
        }
        if (traineeRepository.findByUsername(request.username()).getUser().getActive().equals(request.isActive())) {
            throw new RuntimeException("Trainee with username " + request.username() + " already has status " + request.isActive());
        }
        traineeRepository.updateStatus(request.username(), request.isActive());
    }

    public List<TraineeTrainingsResponse> readTraineeTrainings(TraineeTrainingsRequest request) {
        if (!traineeRepository.existsByUsername(request.username())) {
            throw new TraineeNotFoundException("Trainee with username " + request.username() + " not found");
        }
        List<TraineeTrainingsResponse> traineeTrainingsResponses = new ArrayList<>();
        List<Training> trainings = traineeRepository.getTrainings(request.username());
        if (request.trainerUsername() != null) {
            if (!userService.existsByUsername(request.trainerUsername())) {
                throw new RuntimeException("Trainer with username " + request.trainerUsername() + " not found");
            }
            trainings = trainings.stream()
                    .filter(training -> training.getTrainer().getUser().getUsername().equals(request.trainerUsername()))
                    .toList();
        } else if (request.trainingType() != null) {
            if(!trainingTypeRepository.existsByName(request.trainingType())) {
                throw new RuntimeException("Training type with name " + request.trainingType() + " not found");
            }
            trainings = trainings.stream()
                    .filter(training -> training.getTrainer().getTrainingType().getName().equals(request.trainingType()))
                    .toList();
        }
        for (Training training : trainings) {
            traineeTrainingsResponses.add(new TraineeTrainingsResponse(
                    training.getTrainingName(),
                    training.getTrainingDate(),
                    training.getTrainer().getTrainingType().getName(),
                    training.getDuration(),
                    training.getTrainer().getUser().getUsername()
            ));
        }
        return traineeTrainingsResponses;
    }
}
