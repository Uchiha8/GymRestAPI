package com.epam.service;

import com.epam.domain.Trainee;
import com.epam.domain.User;
import com.epam.dto.request.TraineeRegistrationRequest;
import com.epam.dto.response.RegistrationResponse;
import com.epam.repository.TraineeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
