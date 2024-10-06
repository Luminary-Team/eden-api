package com.luminary.apieden.service;

import com.luminary.apieden.model.database.User;
import com.luminary.apieden.model.exception.HttpError;
import com.luminary.apieden.model.request.TokenRequest;
import com.luminary.apieden.model.response.TokenResponse;
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

    public void partialUpdate(String id, Map<String, Object> request) throws HttpError{
        User user = userRepository.findById(Long.parseLong(id))
                .orElseThrow(() -> new HttpError(HttpStatus.BAD_REQUEST, "Usuário não encontrado"));
        boolean verifyVariable = false;
        if (request.containsKey("name")) {
            log.info("[USER] Name {} being updated to {}", user.getName(), (String) request.get("name"));
            user.setName((String) request.get("name"));
            verifyVariable = true;
        } else if (request.containsKey("userName")) {
            log.info("[USER] UserName {} being updated to {}", user.getUserName(), (String) request.get("userName"));
            user.setUserName((String) request.get("userName"));
            verifyVariable = true;
        } else if (request.containsKey("password")) {
            log.info("[USER] Password being updated.");
            String encodedNewPassword = passwordEncoder.encode((String) request.get("password"));
            user.setPassword(encodedNewPassword);
            verifyVariable = true;
        } else if (request.containsKey("rating")) {
            log.info("[USER] Rating {} being updated to {}", user.getRating(), (String) request.get("password"));
            user.setRating((Float) request.get("rating"));
            verifyVariable = true;
        } else if (request.containsKey("cellphone")) {
            log.info("[USER] Cellphone {} being updated to {}", user.getCellphone(), (String) request.get("cellphone"));
            user.setCellphone((String) request.get("cellphone"));
            verifyVariable = true;
        }
        if (!verifyVariable) {
            log.warn("None valid field passed.");
            throw new HttpError(HttpStatus.BAD_REQUEST, "Nenhum campo válido foi passado.");
        }

        log.info("Starting attributes validation.");
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        if (!violations.isEmpty()) {
            StringBuilder errorMessage = new StringBuilder("Erros de validação:");
            for (ConstraintViolation<User> violation : violations) {
                errorMessage.append(" /  ").append(violation.getMessage());
            }
            throw new HttpError(HttpStatus.BAD_REQUEST, errorMessage.toString());
        }

        log.info("Attributes validated.");
        log.info("Saving user in database.");
        userRepository.save(user);
    }

    private void checkUnique(User user) throws HttpError {
        if (userRepository.findByCpf(user.getCpf()).isPresent()) {
            log.error("Error creating user with Cpf {}, is already registered", user.getCpf());
            throw new HttpError(HttpStatus.BAD_REQUEST, "Cpf já está registrado");
        } else if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            log.error("Error creating user with Email {}, is already registered", user.getEmail());
            throw new HttpError(HttpStatus.BAD_REQUEST, "Email já está registrado");
        } else if (userRepository.findByUserName(user.getUserName()).isPresent()) {
            log.error("Error creating user with UserName {}, is already registered", user.getUserName());
            throw new HttpError(HttpStatus.BAD_REQUEST, "UserName já está registrado");
        } else if (user.getCellphone() != null
                && userRepository.findByCellphone(user.getCellphone()).isPresent()) {
            log.error("Error creating user with Phone {}, is already registered", user.getCellphone());
            throw new HttpError(HttpStatus.BAD_REQUEST, "Phone já está registrado");
        }
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findByParameter(final String id, final String cpf, final String email) {
        User user = null;
        log.info("Trying to fetch user by valid parameter");
        if (id != null) {
            log.info("Fetching user by id: {}", id);
            user = userRepository.findById(Long.valueOf(id))
                    .orElseThrow(() -> new HttpError(HttpStatus.BAD_REQUEST, "Id não registrado"));
        } else if (cpf != null) {
            log.info("Fetching user by cpf: {}", cpf);
            user = userRepository.findByCpf(cpf)
                    .orElseThrow(() -> new HttpError(HttpStatus.BAD_REQUEST, "Cpf não registrado"));
        } else if (email != null) {
            log.info("Fetching user by email: {}", email);
            user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new HttpError(HttpStatus.BAD_REQUEST, "Email não registrado"));
        }
        if (user == null) {
            log.warn("None valid parameter was passed, user not found");
            throw new HttpError(HttpStatus.BAD_REQUEST, "Usuário não encontrado");
        }
        log.info("Returning user {}", user);
        return user;
    }

    public TokenResponse token(TokenRequest tokenRequest) throws HttpError {
        User user = userRepository.findByEmail(tokenRequest.getEmail())
                .orElseThrow(() ->  new HttpError(HttpStatus.BAD_REQUEST, "Usuário não encontrado"));
        if (user != null) {
            try {
                String token = Jwts.builder()
                        .setSubject(user.getEmail())
                        .setExpiration(new Date(System.currentTimeMillis() + 86_400_000)) // 1 day
                        .signWith(secretKey, SignatureAlgorithm.HS512)
                        .compact();
                log.info("Generated Token: {}", token);
                return new TokenResponse(token);
            } catch (Exception e) {
                log.error("Error ao gerar o token JWT", e);
                throw new HttpError(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao gerar o token JWT");
            }
        } else {
            log.error("Invalid credentials for email: {}", tokenRequest.getEmail());
            throw new HttpError(HttpStatus.UNAUTHORIZED, "Credenciais inválidas");
        }
    }

    public void deleteById(String id) {
        userRepository.deleteById(Long.valueOf(id));
    }
}
