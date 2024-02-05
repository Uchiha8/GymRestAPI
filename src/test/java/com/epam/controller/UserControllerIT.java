package com.epam.controller;

import com.epam.domain.User;
import com.epam.dto.request.ChangeLogin;
import com.epam.dto.request.Login;
import com.epam.service.UserService;
import com.epam.utils.validation.ValidModule;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
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

public class UserControllerIT {
    private final UserService userService = Mockito.mock(UserService.class);
    private final ValidModule validModule = Mockito.mock(ValidModule.class);
    private final UserController userController = new UserController(userService, validModule);
    private final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

    @Test
    public void testLogin() throws Exception {
        String username = "ali.najimov";
        Login request = new Login(username, "password");
        User user = new User("Ali", "Najimov", "ali.najimov", "password", true);
        when(userService.findByUsername(username)).thenReturn(user);
        ObjectMapper objectMapper = new ObjectMapper();
        String body = objectMapper.writeValueAsString(request);
        ResultActions resultActions = mockMvc.perform(get("/api/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body));
        resultActions.andExpect(status().is(200));
    }

    @Test
    public void testChangePassword() throws Exception {
        String username = "ali.najimov";
        String oldPassword = "oldPassword";
        String newPassword = "newPassword";
        ChangeLogin request = new ChangeLogin(username, oldPassword, newPassword);
        User user = new User("Ali", "Najimov", "ali.najimov", oldPassword, true);
        when(userService.findByUsername(username)).thenReturn(user);
        ObjectMapper objectMapper = new ObjectMapper();
        String body = objectMapper.writeValueAsString(request);
        ResultActions resultActions = mockMvc.perform(put("/api/users/changePassword")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body));
        resultActions.andExpect(status().is(200));
    }
}
