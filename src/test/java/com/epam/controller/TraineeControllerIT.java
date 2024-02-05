package com.epam.controller;

import com.epam.domain.TrainingType;
import com.epam.dto.request.StatusRequest;
import com.epam.dto.request.TraineeRegistrationRequest;
import com.epam.dto.request.TraineeTrainingsRequest;
import com.epam.dto.request.UpdateTraineeRequest;
import com.epam.dto.response.RegistrationResponse;
import com.epam.dto.response.TraineeProfileResponse;
import com.epam.dto.response.TraineeTrainingsResponse;
import com.epam.dto.response.UpdateTraineeResponse;
import com.epam.service.TraineeService;
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

public class TraineeControllerIT {
    private final TraineeService traineeService = Mockito.mock(TraineeService.class);
    private final ValidModule validModule = Mockito.mock(ValidModule.class);
    private final TraineeController traineeController = new TraineeController(traineeService, validModule);
    private final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(traineeController).build();

    @Test
    public void testPostRegister() throws Exception {
        Date dateOfBirth = new Date();
        TraineeRegistrationRequest request = new TraineeRegistrationRequest("username", "Alisher", dateOfBirth, "firstName");
        when(traineeService.save(request)).thenReturn(new RegistrationResponse("username", "password"));
        ObjectMapper objectMapper = new ObjectMapper();
        String body = objectMapper.writeValueAsString(request);
        ResultActions resultActions = mockMvc.perform(post("/api/trainees/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body));
        resultActions.andExpect(status().isCreated());
    }

    @Test
    public void testGetTraineeProfile() throws Exception {
        String username = "validUsername";
        TraineeProfileResponse response = new TraineeProfileResponse("Alisher", "Najimov", new Date(), "address", true, List.of());
        when(traineeService.findByUsername(username)).thenReturn(response);
        ResultActions resultActions = mockMvc.perform(get("/api/trainees/profile?username=" + username));
        resultActions.andExpect(status().is(200));
    }

    @Test
    public void testGetAllTraineeTrainings() throws Exception {
        Date date = new Date();
        TrainingType trainingType = new TrainingType("Java");
        TraineeTrainingsRequest request = new TraineeTrainingsRequest("username", date, date, "Java", "trainerUsername");
        List<TraineeTrainingsResponse> responses = new ArrayList<>();
        responses.add(new TraineeTrainingsResponse("Avengers", date, "Java", Duration.ofHours(2), "trainerUsername"));
        when(traineeService.readTraineeTrainings(request)).thenReturn(responses);
        ObjectMapper objectMapper = new ObjectMapper();
        String body = objectMapper.writeValueAsString(request);
        ResultActions resultActions = mockMvc.perform(get("/api/trainees/readAll/trainings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body));
        resultActions.andExpect(status().is(200));
    }

    @Test
    public void testPutUpdate() throws Exception {
        Date dateOfBirth = new Date();
        UpdateTraineeRequest request = new UpdateTraineeRequest("username", "Alisher", "Najimov", dateOfBirth, "address", true);
        UpdateTraineeResponse response = new UpdateTraineeResponse("username", "Alisher", "Najimov", dateOfBirth, "address", true, List.of());
        when(traineeService.update(request)).thenReturn(response);
        ObjectMapper objectMapper = new ObjectMapper();
        String body = objectMapper.writeValueAsString(request);
        ResultActions resultActions = mockMvc.perform(put("/api/trainees/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body));
        resultActions.andExpect(status().is(200));
    }

    @Test
    public void testDeleteTrainee() throws Exception {
        String username = "validUsername";
        ResultActions resultActions = mockMvc.perform(delete("/api/trainees/delete?username=" + username));
        resultActions.andExpect(status().is(200));
    }

    @Test
    public void testUpdateStatus() throws Exception {
        StatusRequest request = new StatusRequest("username", true);
        ObjectMapper objectMapper = new ObjectMapper();
        String body = objectMapper.writeValueAsString(request);
        ResultActions resultActions = mockMvc.perform(patch("/api/trainees/updateStatus")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body));
        resultActions.andExpect(status().is(200));
    }
}