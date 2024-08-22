package com.luminary.apieden.controllers;

import com.luminary.apieden.models.database.User;
import com.luminary.apieden.models.request.LoginRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {


    @PostMapping("/login")
    public String login(@RequestBody LoginRequest userRequest) {
        return "login";
    }

    @PostMapping("/register")
    public String register(@RequestBody User userRequest) {
        return "register";
    }
}
