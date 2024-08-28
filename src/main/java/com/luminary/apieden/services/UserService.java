package com.luminary.apieden.services;

import com.luminary.apieden.models.database.User;
import com.luminary.apieden.models.request.LoginRequest;
import com.luminary.apieden.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User register(User user) throws RuntimeException {
        checkUnique(user);
        return userRepository.save(user);
    }

    public User login(LoginRequest loginRequest) {
        User user = userRepository.findByCpf(loginRequest.getCpf());
        if(user != null &&
                user.getPassword().equals(loginRequest.getPassword())) {
            return user;
        }
        throw new RuntimeException("Invalid 'cpf' or 'password'");
    }

    private void checkPhone() {

    }

    private void checkUnique(User user) throws RuntimeException {
        if (userRepository.findByCpf(user.getCpf()) != null) {
            throw new RuntimeException("Cpf already registered");
        } else if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("Email already registered");
        } else if (userRepository.findByUsername(user.getUserName()) != null) {
            throw new RuntimeException("Username already registered");
        }
    }
}
