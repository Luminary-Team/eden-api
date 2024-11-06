package com.luminary.apieden.model.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class CreateUserRequest {
    private long userId;
    private String userName;
}
