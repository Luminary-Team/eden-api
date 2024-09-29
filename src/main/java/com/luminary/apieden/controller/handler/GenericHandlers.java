package com.luminary.apieden.controller.handler;

import com.luminary.apieden.mapper.ErrorMapper;
import com.luminary.apieden.model.exception.HttpError;
import com.luminary.apieden.model.response.ErrorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Slf4j
public class GenericHandlers {
    private final ErrorMapper errorMapper;
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, Object> necessaryValidations(BindingResult result) {
        Map<String, Object> errors = new HashMap<>();
        for (FieldError error: result.getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return errors;
    }

    @ExceptionHandler(HttpError.class)
    public ResponseEntity<ErrorResponse> genericHandler(HttpError error) {
        log.error("An error occurred. {}", error.getMessage());
        return ResponseEntity.status(error.getHttpStatus()).body(errorMapper.toErrorResponse(error));
    }
}