package com.epam.utils.validation;

import com.epam.domain.TrainingType;
import com.epam.dto.request.ChangeLogin;
import com.epam.dto.request.Login;
import com.epam.dto.request.TraineeRegistrationRequest;
import com.epam.dto.request.TrainerRegistrationRequest;
import com.epam.utils.exception.UserNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class ValidModule {

    public void traineeRegistration(TraineeRegistrationRequest request) {
        if (request.firstName() == null) {
            throw new IllegalArgumentException("First name is null");
        } else if (request.lastName() == null) {
            throw new IllegalArgumentException("Last name is null");
        }
    }

    public void trainerRegistration(TrainerRegistrationRequest request) {
        if (request.firstName() == null) {
            throw new IllegalArgumentException("First name is null");
        } else if (request.lastName() == null) {
            throw new IllegalArgumentException("Last name is null");
        } else if (request.trainingType() == null) {
            throw new IllegalArgumentException("Training type is null");
        }
    }

    public void login(Login request) {
        if (request.username() == null) {
            throw new IllegalArgumentException("Username is null");
        } else if (request.password() == null) {
            throw new IllegalArgumentException("Password is null");
        }
    }

    public void changeLogin(ChangeLogin request) {
        if (request.username() == null) {
            throw new IllegalArgumentException("Username is null");
        } else if (request.oldPassword() == null) {
            throw new IllegalArgumentException("OldPassword is null");
        } else if (request.newPassword() == null) {
            throw new IllegalArgumentException("NewPassword is null");
        }
    }

    public void trainingTypeValid(TrainingType request) {
        if (request == null) {
            throw new IllegalArgumentException("Training type is null");
        } else if (request.getName() == null) {
            throw new IllegalArgumentException("Training type name is null");
        }
    }
}
