package com.luminary.apieden.controllers;

import com.luminary.apieden.models.database.User;
import com.luminary.apieden.models.request.LoginRequest;
import com.luminary.apieden.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;
    public UserController (UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest userRequest) {
        return "login";
    }

    @PostMapping("/register")
    public String register(@RequestBody User userRequest) {
        return "register";
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public String badRequest(RuntimeException runtimeException) {
        return runtimeException.getMessage();
    }
}
