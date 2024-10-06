package com.luminary.apieden.controller;

import com.luminary.apieden.controller.contract.UserContract;
import com.luminary.apieden.model.exception.HttpError;
import com.luminary.apieden.model.database.User;
import com.luminary.apieden.model.request.TokenRequest;
import com.luminary.apieden.model.response.TokenResponse;
import com.luminary.apieden.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController implements UserContract {
    private final UserService userService;

    @PostMapping("/token")
    public TokenResponse token(@RequestBody final TokenRequest tokenRequest) {
        return userService.token(tokenRequest);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<User>> findUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }

    @GetMapping("/findByParam")
    public ResponseEntity<User> findUserByParameter(
                                            @RequestParam(value = "id", required = false) String id,
                                            @RequestParam(value = "cpf", required = false) String cpf,
                                            @RequestParam(value = "email", required = false) String email) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findByParameter(id, cpf, email));
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody @Valid User userRequest) {
        log.info("Attempting to save user in database");
        User user = userService.register(userRequest);
        log.info("Saved user in database");

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> partialUpdate(@PathVariable String id, @RequestBody Map<String, Object> request) {
        log.info("Entering in partialUpdate method.");
        userService.partialUpdate(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(request);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable String id) {
        userService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}