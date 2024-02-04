package com.epam.utils.validation;

import com.epam.domain.TrainingType;
import com.epam.dto.request.*;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ValidModuleTest {

    @Test
    void traineeRegistrationTestLastNameNull() {
        ValidModule validModule = new ValidModule();
        TraineeRegistrationRequest request = new TraineeRegistrationRequest("John", null, new Date(), "Address");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> validModule.traineeRegistration(request));

        assert "Last name is null".equals(exception.getMessage());
    }

    @Test
    void trainerRegistration_validRequest_noExceptionThrown() {
        ValidModule validModule = new ValidModule();
        TrainerRegistrationRequest request = new TrainerRegistrationRequest("John", "Doe", new TrainingType("Java Programming"));

        assertDoesNotThrow(() -> validModule.trainerRegistration(request));
    }

    @Test
    void trainerRegistration_nullFirstName_exceptionThrown() {
        ValidModule validModule = new ValidModule();
        TrainerRegistrationRequest request = new TrainerRegistrationRequest(null, "Doe", new TrainingType("Java Programming"));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> validModule.trainerRegistration(request));

        assert "First name is null".equals(exception.getMessage());
    }

    @Test
    void trainerRegistration_nullLastName_exceptionThrown() {
        ValidModule validModule = new ValidModule();
        TrainerRegistrationRequest request = new TrainerRegistrationRequest("John", null, new TrainingType("Java Programming"));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> validModule.trainerRegistration(request));

        assert "Last name is null".equals(exception.getMessage());
    }

    @Test
    void trainerRegistration_nullTrainingType_exceptionThrown() {
        ValidModule validModule = new ValidModule();
        TrainerRegistrationRequest request = new TrainerRegistrationRequest("John", "Doe", null);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> validModule.trainerRegistration(request));

        assert "Training type is null".equals(exception.getMessage());
    }


    @Test
    void login_validRequest_noExceptionThrown() {
        ValidModule validModule = new ValidModule();
        Login request = new Login("john_doe", "password");

        assertDoesNotThrow(() -> validModule.login(request));
    }

    @Test
    void login_nullUsername_exceptionThrown() {
        ValidModule validModule = new ValidModule();
        Login request = new Login(null, "password");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> validModule.login(request));

        assert "Username is null".equals(exception.getMessage());
    }

    @Test
    void login_nullPassword_exceptionThrown() {
        ValidModule validModule = new ValidModule();
        Login request = new Login("john_doe", null);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> validModule.login(request));

        assert "Password is null".equals(exception.getMessage());
    }

    @Test
    void traineeRegistration_validRequest_noExceptionThrown() {
        ValidModule validModule = new ValidModule();
        TraineeRegistrationRequest request = new TraineeRegistrationRequest("John", "Doe", new Date(), "Address");

        assertDoesNotThrow(() -> validModule.traineeRegistration(request));
    }

    @Test
    void traineeRegistration_nullFirstName_exceptionThrown() {
        ValidModule validModule = new ValidModule();
        TraineeRegistrationRequest request = new TraineeRegistrationRequest(null, "Doe", new Date(), "Address");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> validModule.traineeRegistration(request));

        assert "First name is null".equals(exception.getMessage());
    }

    @Test
    void changeLogin_validRequest_noExceptionThrown() {
        ValidModule validModule = new ValidModule();
        ChangeLogin request = new ChangeLogin("john_doe", "old_password", "new_password");

        assertDoesNotThrow(() -> validModule.changeLogin(request));
    }

    @Test
    void changeLogin_nullUsername_exceptionThrown() {
        ValidModule validModule = new ValidModule();
        ChangeLogin request = new ChangeLogin(null, "old_password", "new_password");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> validModule.changeLogin(request));

        assert "Username is null".equals(exception.getMessage());
    }

    @Test
    void changeLogin_nullOldPassword_exceptionThrown() {
        ValidModule validModule = new ValidModule();
        ChangeLogin request = new ChangeLogin("john_doe", null, "new_password");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> validModule.changeLogin(request));

        assert "OldPassword is null".equals(exception.getMessage());
    }

    @Test
    void usernameValid_validUsername_noExceptionThrown() {
        ValidModule validModule = new ValidModule();
        String validUsername = "john_doe";

        assertDoesNotThrow(() -> validModule.usernameValid(validUsername));
    }

    @Test
    void usernameValid_nullUsername_exceptionThrown() {
        ValidModule validModule = new ValidModule();

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> validModule.usernameValid(null));

        assert "Username is null".equals(exception.getMessage());
    }


    @Test
    void updateTrainee_validRequest_noExceptionThrown() {
        ValidModule validModule = new ValidModule();
        UpdateTraineeRequest validRequest = new UpdateTraineeRequest("john_doe", "John", "Doe", new Date(), "Address", true);

        assertDoesNotThrow(() -> validModule.updateTrainee(validRequest));
    }

    @Test
    void updateTrainee_nullUsername_exceptionThrown() {
        ValidModule validModule = new ValidModule();
        UpdateTraineeRequest requestWithNullUsername = new UpdateTraineeRequest(null, "John", "Doe", new Date(), "Address", true);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> validModule.updateTrainee(requestWithNullUsername));

        assert "Username is null".equals(exception.getMessage());
    }

    @Test
    void updateTrainee_nullFirstName_exceptionThrown() {
        ValidModule validModule = new ValidModule();
        UpdateTraineeRequest requestWithNullFirstName = new UpdateTraineeRequest("john_doe", null, "Doe", new Date(), "Address", true);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> validModule.updateTrainee(requestWithNullFirstName));

        assert "First name is null".equals(exception.getMessage());
    }

    @Test
    void updateTrainee_nullLastName_exceptionThrown() {
        ValidModule validModule = new ValidModule();
        UpdateTraineeRequest requestWithNullLastName = new UpdateTraineeRequest("john_doe", "John", null, new Date(), "Address", true);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> validModule.updateTrainee(requestWithNullLastName));

        assert "Last name is null".equals(exception.getMessage());
    }

    @Test
    void updateTrainee_nullIsActive_exceptionThrown() {
        ValidModule validModule = new ValidModule();
        UpdateTraineeRequest requestWithNullIsActive = new UpdateTraineeRequest("john_doe", "John", "Doe", new Date(), "Address", null);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> validModule.updateTrainee(requestWithNullIsActive));

        assert "Is active is null".equals(exception.getMessage());
    }

    @Test
    void updateTrainer_validRequest_noExceptionThrown() {
        ValidModule validModule = new ValidModule();
        TrainingType trainingType = new TrainingType("Java Programming");
        UpdateTrainerRequest validRequest = new UpdateTrainerRequest("john_doe", "John", "Doe", trainingType, true);

        assertDoesNotThrow(() -> validModule.updateTrainer(validRequest));
    }

    @Test
    void updateTrainer_nullUsername_exceptionThrown() {
        ValidModule validModule = new ValidModule();
        TrainingType trainingType = new TrainingType("Java Programming");
        UpdateTrainerRequest requestWithNullUsername = new UpdateTrainerRequest(null, "John", "Doe", trainingType, true);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> validModule.updateTrainer(requestWithNullUsername));

        assert "Username is null".equals(exception.getMessage());
    }

    @Test
    void updateTrainer_nullFirstName_exceptionThrown() {
        ValidModule validModule = new ValidModule();
        UpdateTrainerRequest requestWithNullFirstName = new UpdateTrainerRequest("john_doe", null, "Doe", new TrainingType("Java Programming"), true);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> validModule.updateTrainer(requestWithNullFirstName));

        assert "First name is null".equals(exception.getMessage());
    }

    @Test
    void updateTrainer_nullLastName_exceptionThrown() {
        ValidModule validModule = new ValidModule();
        UpdateTrainerRequest requestWithNullLastName = new UpdateTrainerRequest("john_doe", "John", null, new TrainingType("Java Programming"), true);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> validModule.updateTrainer(requestWithNullLastName));

        assert "Last name is null".equals(exception.getMessage());
    }

    @Test
    void updateTrainer_nullIsActive_exceptionThrown() {
        ValidModule validModule = new ValidModule();
        UpdateTrainerRequest requestWithNullIsActive = new UpdateTrainerRequest("john_doe", "John", "Doe", new TrainingType("Java Programming"), null);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> validModule.updateTrainer(requestWithNullIsActive));

        assert "Is active is null".equals(exception.getMessage());
    }


    @Test
    void trainingRequest_validRequest_noExceptionThrown() {
        ValidModule validModule = new ValidModule();
        Date date = new Date();
        Duration duration = Duration.ofHours(2);
        TrainingRequest validRequest = new TrainingRequest("trainee_username", "trainer_username", "TrainingName", date, duration);

        assertDoesNotThrow(() -> validModule.trainingRequest(validRequest));
    }

    @Test
    void trainingRequest_nullTraineeUsername_exceptionThrown() {
        ValidModule validModule = new ValidModule();
        Date date = new Date();
        Duration duration = Duration.ofHours(2);
        TrainingRequest requestWithNullTraineeUsername = new TrainingRequest(null, "trainer_username", "TrainingName", date, duration);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> validModule.trainingRequest(requestWithNullTraineeUsername));

        assert "Trainee username is null".equals(exception.getMessage());
    }

    @Test
    void trainingRequest_nullTrainerUsername_exceptionThrown() {
        ValidModule validModule = new ValidModule();
        Date date = new Date();
        Duration duration = Duration.ofHours(2);
        TrainingRequest requestWithNullTrainerUsername = new TrainingRequest("trainee_username", null, "TrainingName", date, duration);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> validModule.trainingRequest(requestWithNullTrainerUsername));

        assert "Trainer username is null".equals(exception.getMessage());
    }

    @Test
    void trainingRequest_nullTrainingName_exceptionThrown() {
        ValidModule validModule = new ValidModule();
        Date date = new Date();
        Duration duration = Duration.ofHours(2);
        TrainingRequest requestWithNullTrainingName = new TrainingRequest("trainee_username", "trainer_username", null, date, duration);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> validModule.trainingRequest(requestWithNullTrainingName));

        assert "Training name is null".equals(exception.getMessage());
    }

    @Test
    void trainingRequest_nullTrainingDate_exceptionThrown() {
        ValidModule validModule = new ValidModule();
        Duration duration = Duration.ofHours(2);
        TrainingRequest requestWithNullTrainingDate = new TrainingRequest("trainee_username", "trainer_username", "TrainingName", null, duration);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> validModule.trainingRequest(requestWithNullTrainingDate));

        assert "Training date is null".equals(exception.getMessage());
    }

    @Test
    void trainingRequest_nullDuration_exceptionThrown() {
        ValidModule validModule = new ValidModule();
        Date date = new Date();
        TrainingRequest requestWithNullDuration = new TrainingRequest("trainee_username", "trainer_username", "TrainingName", date, null);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> validModule.trainingRequest(requestWithNullDuration));

        assert "Duration is null".equals(exception.getMessage());
    }

    @Test
    void changeLogin_nullNewPassword_exceptionThrown() {
        ValidModule validModule = new ValidModule();
        ChangeLogin request = new ChangeLogin("john_doe", "old_password", null);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> validModule.changeLogin(request));

        assert "NewPassword is null".equals(exception.getMessage());
    }

    @Test
    void trainingTypeValid_validTrainingType_noExceptionThrown() {
        ValidModule validModule = new ValidModule();
        TrainingType trainingType = new TrainingType("Java Programming");

        assertDoesNotThrow(() -> validModule.trainingTypeValid(trainingType));
    }


    @Test
    void updateStatus_validRequest_noExceptionThrown() {
        ValidModule validModule = new ValidModule();
        StatusRequest validRequest = new StatusRequest("username", true);

        assertDoesNotThrow(() -> validModule.updateStatus(validRequest));
    }

    @Test
    void updateStatus_nullUsername_exceptionThrown() {
        ValidModule validModule = new ValidModule();
        StatusRequest requestWithNullUsername = new StatusRequest(null, true);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> validModule.updateStatus(requestWithNullUsername));

        assert "Username is null".equals(exception.getMessage());
    }

    @Test
    void updateStatus_nullIsActive_exceptionThrown() {
        ValidModule validModule = new ValidModule();
        StatusRequest requestWithNullIsActive = new StatusRequest("username", null);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> validModule.updateStatus(requestWithNullIsActive));

        assert "Is active is null".equals(exception.getMessage());
    }

    @Test
    void traineeTrainings_validRequest_noExceptionThrown() {
        ValidModule validModule = new ValidModule();
        TraineeTrainingsRequest validRequest = new TraineeTrainingsRequest("username", new Date(), new Date(), "Java Programming", "john.doe");

        assertDoesNotThrow(() -> validModule.traineeTrainings(validRequest));
    }

    @Test
    void traineeTrainings_nullUsername_exceptionThrown() {
        ValidModule validModule = new ValidModule();
        TraineeTrainingsRequest requestWithNullUsername = new TraineeTrainingsRequest(null, new Date(), new Date(), "Java Programming", "john.doe");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> validModule.traineeTrainings(requestWithNullUsername));

        assert "Username is nullasdasd".equals(exception.getMessage());
    }

    @Test
    void trainingTypeValid_nullTrainingType_exceptionThrown() {
        ValidModule validModule = new ValidModule();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> validModule.trainingTypeValid(null));

        assert "Training type is null".equals(exception.getMessage());
    }

    @Test
    void trainerTrainings_validUsername_noExceptionThrown() {
        // Arrange
        ValidModule validModule = new ValidModule();
        TrainerTrainingsRequest request = new TrainerTrainingsRequest("validUsername", new Date(), new Date(), null, null);
        // Act & Assert
        assertDoesNotThrow(() -> validModule.trainerTrainings(request));
    }

    @Test
    void trainerTrainings_nullUsername_exceptionThrown() {
        // Arrange
        ValidModule validModule = new ValidModule();
        TrainerTrainingsRequest request = new TrainerTrainingsRequest(null, new Date(), new Date(), null, null);
        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> validModule.trainerTrainings(request));

        // Assert
        assertEquals("Username is null", exception.getMessage());
    }

    @Test
    void trainingTypeValid_nullTrainingTypeName_exceptionThrown() {
        ValidModule validModule = new ValidModule();
        TrainingType trainingType = new TrainingType(null);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> validModule.trainingTypeValid(trainingType));

        assert "Training type name is null".equals(exception.getMessage());
    }
}

