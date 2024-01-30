package com.epam.controller;

import com.epam.domain.User;
import com.epam.dto.request.ChangeLogin;
import com.epam.dto.request.Login;
import com.epam.service.UserService;
import com.epam.utils.validation.ValidModule;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private static final Logger logger = LogManager.getLogger(UserController.class);
    private final UserService userService;
    private final ValidModule validModule;

    @Autowired
    public UserController(UserService userService, ValidModule validModule) {
        this.userService = userService;
        this.validModule = validModule;
    }

    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestBody Login request) {
        try {
            validModule.login(request);
            User user = userService.findByUsername(request.username());
            if (user.getPassword().equals(request.password())) {
                return ResponseEntity.ok().body("Login successful");
            } else {
                return ResponseEntity.badRequest().body("Wrong password");
            }
        } catch (RuntimeException e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/changePassword")
    public ResponseEntity<?> changePassword(@RequestBody ChangeLogin request) {
        try {
            validModule.changeLogin(request);
            userService.updatePassword(request);
            return ResponseEntity.ok().body("Password changed");
        } catch (RuntimeException e) {
            logger.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
