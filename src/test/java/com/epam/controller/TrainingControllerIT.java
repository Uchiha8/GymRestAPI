package com.epam.controller;

import com.epam.domain.*;
import com.epam.dto.request.TrainingRequest;
import com.epam.service.TrainingService;
import com.epam.utils.validation.ValidModule;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.Duration;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class TrainingControllerIT {
    private final TrainingService trainingService = Mockito.mock(TrainingService.class);
    private final ValidModule validModule = Mockito.mock(ValidModule.class);
    private final TrainingController trainingController = new TrainingController(trainingService, validModule);
    private final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(trainingController).build();

    @Test
    public void testPostRegister() throws Exception {
        TrainingRequest request = new TrainingRequest("ali.doe", "john.solman", "Avengers", new Date(), null);
        TrainingType trainingType = new TrainingType("Java");
        User user = new User("Ali", "Najimov", "ali.najimov", "password", true);
        Trainer trainer = new Trainer(trainingType, user, List.of());
        Trainee trainee = new Trainee(new Date(), "address", user, List.of());
        Training response = new Training(trainee, trainer, "Avengers", trainingType, new Date(), null);
        when(trainingService.save(request)).thenReturn(response);
        ObjectMapper objectMapper = new ObjectMapper();
        String body = objectMapper.writeValueAsString(request);
        ResultActions resultActions = mockMvc.perform(post("/api/trainings/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body));
        resultActions.andExpect(status().isCreated());
    }
}
