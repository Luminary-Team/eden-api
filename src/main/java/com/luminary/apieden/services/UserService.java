package com.luminary.apieden.services;

import com.luminary.apieden.models.database.User;
import com.luminary.apieden.models.request.LoginRequest;
import com.luminary.apieden.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
        User user = userRepository.findByEmail(loginRequest.getEmail());
        if(user != null &&
                user.getPassword().equals(loginRequest.getPassword())) {
            return user;
        }
        throw new RuntimeException("Invalid 'email' or 'password'");
    }

    private void checkUnique(User user) throws RuntimeException {
        if (userRepository.findByCpf(user.getCpf()) != null) {
            throw new RuntimeException("Cpf already registered");
        } else if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("Email already registered");
        } else if (userRepository.findByUsername(user.getUserName()) != null) {
            throw new RuntimeException("Username already registered");
        } else if (user.getPhone() != null
                && userRepository.findByPhone(user.getPhone()) != null) {
            throw new RuntimeException("Phone already registered");
        }
    }

    public User findById(String id) {
        return userRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new RuntimeException("ID not registered"));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
