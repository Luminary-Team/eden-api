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

    public User register(User user) {
        return userRepository.save(user);
    }

    public User login(LoginRequest loginRequest) {
        List<User> userList = userRepository.findByCpf(loginRequest.getCpf());
        if(!userList.isEmpty() &&
                userList.get(0).getPassword().equals(loginRequest.getPassword())) {
            return userList.get(0);
        }
        throw new RuntimeException("Invalid 'email' or 'password'");
    }
}
