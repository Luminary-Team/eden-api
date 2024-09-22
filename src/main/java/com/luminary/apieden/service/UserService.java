package com.luminary.apieden.service;

import com.luminary.apieden.model.database.User;
import com.luminary.apieden.model.exception.HttpError;
import com.luminary.apieden.model.request.TokenRequest;
import com.luminary.apieden.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final SecretKey secretKey;
    private final UserRepository userRepository;

    public User register(User user) throws HttpError {
        log.info("Checking unique fields");
        checkUnique(user);
        log.info("None unique field repeated");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public void parcialUpdate(String id, Map<String, Object> request) {
        User user = userRepository.findById(Long.parseLong(id))
                .orElseThrow(() -> new HttpError(HttpStatus.BAD_REQUEST, "User not found"));

        if (request.containsKey("name")) {
            user.setName((String) request.get("name"));
        } else if (request.containsKey("userName")) {
            user.setUserName((String) request.get("userName"));
        } else if (request.containsKey("password")) {
            String encodedNewPassword = passwordEncoder.encode((String) request.get("password"));
            user.setPassword(encodedNewPassword);
        } else if (request.containsKey("rating")) {
            user.setRating((Float) request.get("rating"));
        } else if (request.containsKey("cellphone")) {
            user.setCellphone((String) request.get("cellphone"));
        }

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        if (!violations.isEmpty()) {
            // Se houver violações de validação, lance uma exceção ou trate conforme necessário
            StringBuilder errorMessage = new StringBuilder("Erros de validação:");
            for (ConstraintViolation<User> violation : violations) {
                errorMessage.append(" /  ").append(violation.getMessage());
            }
            throw new HttpError(HttpStatus.BAD_REQUEST, errorMessage.toString());
        }

        userRepository.save(user);
    }

    private void checkUnique(User user) throws HttpError {
        if (userRepository.findByCpf(user.getCpf()).isPresent()) {
            log.error("Error creating user with Cpf {}, is already registered", user.getCpf());
            throw new HttpError(HttpStatus.BAD_REQUEST, "Cpf already registered");
        } else if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            log.error("Error creating user with Email {}, is already registered", user.getEmail());
            throw new HttpError(HttpStatus.BAD_REQUEST, "Email already registered");
        } else if (userRepository.findByUserName(user.getUserName()).isPresent()) {
            log.error("Error creating user with UserName {}, is already registered", user.getUserName());
            throw new HttpError(HttpStatus.BAD_REQUEST, "UserName already registered");
        } else if (user.getCellphone() != null
                && userRepository.findByCellphone(user.getCellphone()).isPresent()) {
            log.error("Error creating user with Phone {}, is already registered", user.getCellphone());
            throw new HttpError(HttpStatus.BAD_REQUEST, "Phone already registered");
        }
    }

    public User findById(String id) {
        return userRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new HttpError(HttpStatus.BAD_REQUEST, "ID not registered"));
    }

    public User findByCpf(String cpf) {
        return userRepository.findByCpf(cpf)
                .orElseThrow(() -> new HttpError(HttpStatus.BAD_REQUEST, "Cpf not registered"));
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new HttpError(HttpStatus.BAD_REQUEST, "Email not registered"));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Map<String, String> token(TokenRequest tokenRequest) throws HttpError {
        User user = userRepository.findByEmail(tokenRequest.getEmail())
                .orElseThrow(() ->  new HttpError(HttpStatus.BAD_REQUEST, "User not found"));
        if (user != null && passwordEncoder.matches(tokenRequest.getPassword(), user.getPassword())) {
            try {
                String token = Jwts.builder()
                        .setSubject(user.getEmail())
                        .setExpiration(new Date(System.currentTimeMillis() + 86_400_000)) // 1 day
                        .signWith(secretKey, SignatureAlgorithm.HS512) // Usa a chave secreta para assinar
                        .compact();


                log.info("Generated Token: {}", token);
                return Map.of("token", "Bearer " + token);
            } catch (Exception e) {
                log.error("Error ao gerar o token JWT", e);
                throw new HttpError(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao gerar o token JWT");
            }
        } else {
            log.error("Invalid credentials for email: {}", tokenRequest.getEmail());
            throw new HttpError(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }
    }

    public void deleteById(String id) {
        userRepository.deleteById(Long.valueOf(id));
    }
}
