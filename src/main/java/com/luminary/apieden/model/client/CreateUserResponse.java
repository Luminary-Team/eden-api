package com.luminary.apieden.model.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateUserResponse {
    private String status;
    private User user;

    private static class User {
        private long id;
        private String name;
    }
}
