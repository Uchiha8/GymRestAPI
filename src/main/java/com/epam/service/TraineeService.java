package com.epam.service;

import com.epam.domain.Trainee;
import com.epam.domain.Trainer;
import com.epam.domain.Training;
import com.epam.domain.User;
import com.epam.dto.request.TraineeRegistrationRequest;
import com.epam.dto.request.UpdateTraineeRequest;
import com.epam.dto.response.RegistrationResponse;
import com.epam.dto.response.TraineeProfileResponse;
import com.epam.dto.response.TrainersList;
import com.epam.dto.response.UpdateTraineeResponse;
import com.epam.repository.TraineeRepository;
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

    @Autowired
    public TraineeService(TraineeRepository traineeRepository, UserService userService) {
        this.traineeRepository = traineeRepository;
        this.userService = userService;
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

}
