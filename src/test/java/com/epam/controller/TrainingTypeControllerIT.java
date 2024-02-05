package com.epam.controller;

import com.epam.domain.TrainingType;
import com.epam.service.TrainingTypeService;
import com.epam.utils.validation.ValidModule;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TrainingTypeControllerIT {
    private final TrainingTypeService trainingTypeService = Mockito.mock(TrainingTypeService.class);
    private final ValidModule validModule = Mockito.mock(ValidModule.class);
    private final TrainingTypeController trainingTypeController = new TrainingTypeController(trainingTypeService, validModule);
    private final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(trainingTypeController).build();

    @Test
    public void testPostTrainingType() throws Exception {
        TrainingType request = new TrainingType("Java");
        ObjectMapper objectMapper = new ObjectMapper();
        String body = objectMapper.writeValueAsString(request);
        when(trainingTypeService.save(request)).thenReturn(request);
        ResultActions resultActions = mockMvc.perform(post("/api/trainingTypes/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body));
        resultActions.andExpect(status().isCreated());
    }

    @Test
    public void testGetAllTrainingTypes() throws Exception {
        TrainingType trainingType = new TrainingType("Java");
        when(trainingTypeService.findAll()).thenReturn(java.util.List.of(trainingType));
        ResultActions resultActions = mockMvc.perform(get("/api/trainingTypes/findAll"));
        resultActions.andExpect(status().is(200));
    }
}
