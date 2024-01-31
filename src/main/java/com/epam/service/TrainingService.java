package com.epam.service;

import com.epam.domain.Training;
import com.epam.domain.TrainingType;
import com.epam.dto.request.TrainingRequest;
import com.epam.repository.TraineeRepository;
import com.epam.repository.TrainerRepository;
import com.epam.repository.TrainingRepository;
import com.epam.utils.exception.TraineeNotFoundException;
import com.epam.utils.exception.TrainerNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class TrainingService {
    private final TrainingRepository trainingRepository;
    private final TraineeRepository traineeRepository;
    private final TrainerRepository trainerRepository;

    public TrainingService(TrainingRepository trainingRepository, TraineeRepository traineeRepository, TrainerRepository trainerRepository) {
        this.trainingRepository = trainingRepository;
        this.traineeRepository = traineeRepository;
        this.trainerRepository = trainerRepository;
    }


    public Training save(TrainingRequest request) {
        if (!traineeRepository.existsByUsername(request.traineeUsername())) {
            throw new TraineeNotFoundException("Trainee with username: " + request.traineeUsername() + " not found!!!");
        }
        if (!trainerRepository.existsByUsername(request.trainerUsername())) {
            throw new TrainerNotFoundException("Trainer with username: " + request.trainerUsername() + " not found!!!");
        }
        if (trainingRepository.existsByName(request.trainingName())) {
            throw new IllegalArgumentException("Training with name: " + request.trainingName() + " already exists!!!");
        }
        TrainingType trainingType = trainerRepository.findByUsername(request.trainerUsername()).getTrainingType();
        Training training = new Training(
                traineeRepository.findByUsername(request.traineeUsername()),
                trainerRepository.findByUsername(request.trainerUsername()),
                request.trainingName(),
                trainingType,
                request.trainingDate(),
                request.duration());
        return trainingRepository.save(training);
    }
}
