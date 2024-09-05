package com.luminary.apieden.services;

import com.luminary.apieden.models.database.User;
import com.luminary.apieden.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    public void parcialUpdate(String id, Map<String, Object> request) {
        User user = userRepository.findById(Long.parseLong(id))
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (request.containsKey("name")) {
            user.setName((String) request.get("name"));
        } else if (request.containsKey("userName")) {
            user.setUserName((String) request.get("userName"));
        } else if (request.containsKey("password")) {
            user.setPassword((String) request.get("password"));
        } else if (request.containsKey("avaliation")) {
            user.setAvaliation((Float) request.get("avaliation"));
        } else if (request.containsKey("email")) {
            user.setEmail((String) request.get("email"));
        } else if (request.containsKey("phone")) {
            user.setPhone((String) request.get("phone"));
        } else if (request.containsKey("profileImageUrl")) {
            user.setProfileImageUrl((String) request.get("profileImageUrl"));
        }
        userRepository.save(user);
    }

    private void checkUnique(User user) throws RuntimeException {
        if (userRepository.findByCpf(user.getCpf()) != null) {
            throw new RuntimeException("Cpf already registered");
        } else if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("Email already registered");
        } else if (userRepository.findByUserName(user.getUserName()) != null) {
            throw new RuntimeException("UserName already registered");
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
