package com.luminary.apieden.controllers;

import com.luminary.apieden.models.database.User;
import com.luminary.apieden.models.request.LoginRequest;
import com.luminary.apieden.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    public UserController (UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(String id) {
        if (id == null) {
            throw new RuntimeException("ID required to get user");
        }
        return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
    }

    @GetMapping("/user")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody @Valid LoginRequest loginRequest) {
        if (loginRequest.getCpf() == null || loginRequest.getPassword() == null) {
            throw new RuntimeException("Invalid 'email' or 'password'");
        }
        return ResponseEntity.status(HttpStatus.OK).body(userService.login(loginRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody @Valid User userRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.register(userRequest));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, Object> verifyUser(BindingResult result) {
        Map<String, Object> errors = new HashMap<>();
        for (FieldError error: result.getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return errors;
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public String loginBadRequest(RuntimeException exception) {
        return exception.getMessage();
    }
}
