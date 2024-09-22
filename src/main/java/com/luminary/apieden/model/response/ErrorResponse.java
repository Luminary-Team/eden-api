package com.luminary.apieden.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class ErrorResponse {
    @Schema(name = "httpStatus", description = "Error status from call", example = "400")
    private HttpStatus httpStatus;
    @Schema(name = "message", description = "Error message", example = "Cpf already registered")
    private String message;
}
