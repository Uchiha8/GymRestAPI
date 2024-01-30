package com.epam.service;

import com.epam.domain.TrainingType;
import com.epam.repository.TrainingTypeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class TrainingTypeService {
    private final TrainingTypeRepository trainingTypeRepository;

    @Autowired
    public TrainingTypeService(TrainingTypeRepository trainingTypeRepository) {
        this.trainingTypeRepository = trainingTypeRepository;
    }

    public TrainingType save(TrainingType trainingType) {
        if (existsByName(trainingType.getName())) {
            throw new IllegalArgumentException("Training type with name " + trainingType.getName() + " already exists");
        }
        return trainingTypeRepository.save(trainingType);
    }

    public boolean existsByName(String name) {
        return trainingTypeRepository.existsByName(name);
    }

    public List<TrainingType> findAll() {
        List<TrainingType> trainingTypes = trainingTypeRepository.findAll();
        if (trainingTypes.isEmpty()) {
            throw new RuntimeException("No training types found");
        }
        return trainingTypes;
    }

    public TrainingType findByName(String name) {
        TrainingType trainingType = trainingTypeRepository.findByName(name);
        if (trainingType != null) {
            return trainingType;
        }
        throw new RuntimeException("Training type with name " + name + " not found");
    }
}
