package com.luminary.apieden.controller;

import com.luminary.apieden.controller.contract.UserContract;
import com.luminary.apieden.model.exception.HttpError;
import com.luminary.apieden.model.database.User;
import com.luminary.apieden.model.response.ErrorResponse;
import com.luminary.apieden.model.request.TokenRequest;
import com.luminary.apieden.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController implements UserContract {
    private final UserService userService;

    @PostMapping("/token")
    public Map<String, String> token(@RequestBody final TokenRequest tokenRequest) {
        return userService.token(tokenRequest);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<User>> findUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }

    @GetMapping("/getParam")
    public ResponseEntity<User> findUserById(
                                            @RequestParam(value = "userId", required = false) String userId,
                                            @RequestParam(value = "cpf", required = false) String cpf,
                                            @RequestParam(value = "email", required = false) String email) {
        User user = null;
        log.info("Trying to fetch user by valid parameter");
        if (userId != null) {
            log.info("Fetching user by id: {}", userId);
            user = userService.findById(userId);
        } else if (cpf != null) {
            log.info("Fetching user by cpf: {}", cpf);
            user = userService.findByCpf(cpf);
        } else if (email != null) {
            log.info("Fetching user by email: {}", email);
            user = userService.findByEmail(email);
        }
        if (user == null) {
            log.warn("None valid parameter was passed, user not found");
            throw new HttpError(HttpStatus.BAD_REQUEST, "User was not found");
        }
        log.info("Returning user");
        return ResponseEntity.status(HttpStatus.OK).body(user);
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


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, Object> verifyUser(BindingResult result) {
        log.error("BAD REQUEST error. {}", result);
        Map<String, Object> errors = new HashMap<>();
        for (FieldError error: result.getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return errors;
    }

    @ExceptionHandler(HttpError.class)
    public ResponseEntity<ErrorResponse> genericHandler(HttpError error) {
        log.error("An error occurred. {}", error.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(error.getStatus(), error.getMessage());
        return ResponseEntity.status(errorResponse.getHttpStatus()).body(errorResponse);
    }
}
