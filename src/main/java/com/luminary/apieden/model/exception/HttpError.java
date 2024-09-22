package com.luminary.apieden.model.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class HttpError extends RuntimeException{
    private final HttpStatus status;
    private final String message;
}
