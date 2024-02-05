package com.epam.controller;

import com.epam.domain.TrainingType;
import com.epam.dto.request.StatusRequest;
import com.epam.dto.request.TrainerRegistrationRequest;
import com.epam.dto.request.TrainerTrainingsRequest;
import com.epam.dto.request.UpdateTrainerRequest;
import com.epam.dto.response.RegistrationResponse;
import com.epam.dto.response.TrainerProfileResponse;
import com.epam.dto.response.TrainerTrainingsResponse;
import com.epam.dto.response.UpdateTrainerResponse;
import com.epam.service.TrainerService;
import com.epam.utils.validation.ValidModule;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class TrainerControllerIT {
    private final TrainerService trainerService = Mockito.mock(TrainerService.class);
    private final ValidModule validModule = Mockito.mock(ValidModule.class);
    private final TrainerController trainerController = new TrainerController(trainerService, validModule);
    private final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(trainerController).build();

    @Test
    public void testPostRegister() throws Exception {
        TrainerRegistrationRequest request = new TrainerRegistrationRequest("username", "Alisher", new TrainingType("Java"));
        when(trainerService.save(request)).thenReturn(new RegistrationResponse("username", "password"));
        ObjectMapper objectMapper = new ObjectMapper();
        String body = objectMapper.writeValueAsString(request);
        ResultActions resultActions = mockMvc.perform(post("/api/trainers/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body));
        resultActions.andExpect(status().isCreated());
    }

    @Test
    public void testGetTraineeProfile() throws Exception {
        String username = "alisher.najimov";
        TrainerProfileResponse response = new TrainerProfileResponse("Alisher", "Najimov", new TrainingType("Java"), true, List.of());
        when(trainerService.findByUsername(username)).thenReturn(response);
        ResultActions resultActions = mockMvc.perform(get("/api/trainers/profile?username=" + username));
        resultActions.andExpect(status().is(200));
    }

    @Test
    public void testUpdateTrainer() throws Exception {
        String username = "alisher.najimov";
        UpdateTrainerRequest request = new UpdateTrainerRequest(username, "Alisher", "Najimov", new TrainingType("Java"), true);
        UpdateTrainerResponse response = new UpdateTrainerResponse(username, "Alisher", "Najimov", new TrainingType("Java"), true, List.of());
        when(trainerService.update(request)).thenReturn(response);
        ObjectMapper objectMapper = new ObjectMapper();
        String body = objectMapper.writeValueAsString(request);
        ResultActions resultActions = mockMvc.perform(put("/api/trainers/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body));
        resultActions.andExpect(status().is(200));
    }

    @Test
    public void testGetAllTraineeTrainings() throws Exception {
        Date date = new Date();
        TrainerTrainingsRequest request = new TrainerTrainingsRequest("alisher.najimov", date, date, null, null);
        List<TrainerTrainingsResponse> response = new ArrayList<>();
        response.add(new TrainerTrainingsResponse("Avengers", date, "Java", Duration.ofHours(2), "traineeUsername"));
        when(trainerService.readTrainerTrainings(request)).thenReturn(response);
        ObjectMapper objectMapper = new ObjectMapper();
        String body = objectMapper.writeValueAsString(request);
        ResultActions resultActions = mockMvc.perform(get("/api/trainers/readAll/trainings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body));
        resultActions.andExpect(status().is(200));
    }

    @Test
    public void testUpdateStatus() throws Exception {
        String username = "alisher.najimov";
        StatusRequest request = new StatusRequest(username, true);
        ObjectMapper objectMapper = new ObjectMapper();
        String body = objectMapper.writeValueAsString(request);
        ResultActions resultActions = mockMvc.perform(patch("/api/trainers/updateStatus")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body));
        resultActions.andExpect(status().is(200));
    }
}

