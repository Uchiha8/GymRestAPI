package com.epam.service;

import com.epam.domain.User;
import com.epam.dto.request.ChangeLogin;
import com.epam.repository.UserRepository;
import com.epam.utils.exception.UserNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {
        user.setUsername(usernameGenerator(user.getFirstName(), user.getLastName()));
        user.setPassword(passwordGenerator());
        return userRepository.save(user);
    }

    public User findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return user;
        }
        throw new UserNotFoundException("User with username " + username + " not found");
    }

    public User update(User user) {
        return userRepository.update(user);
    }

    public void updatePassword(ChangeLogin request) {
        User user = findByUsername(request.username());
        if (user != null) {
            if (user.getPassword().equals(request.oldPassword())) {
                user.setPassword(request.newPassword());
                userRepository.updatePassword(user);
            } else {
                throw new RuntimeException("Old password is incorrect");
            }
        } else {
            throw new UserNotFoundException("User with username " + request.username() + " not found");
        }
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public String usernameGenerator(String firstName, String lastName) {
        String username = firstName.toLowerCase() + "." + lastName.toLowerCase();
        if (existsByUsername(username)) {
            int i = 1;
            while (existsByUsername(username + i)) {
                i++;
            }
            username = username + i;
        }
        return username;
    }

    public String passwordGenerator() {
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            password.append((char) (Math.random() * 26 + 97));
        }
        return password.toString();
    }

}
