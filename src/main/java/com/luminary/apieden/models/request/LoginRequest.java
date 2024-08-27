package com.luminary.apieden.models.request;

import lombok.Getter;

@Getter
public class LoginRequest {
    private String cpf;
    private String password;
}
