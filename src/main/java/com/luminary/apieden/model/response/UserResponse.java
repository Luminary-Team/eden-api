package com.luminary.apieden.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserResponse {
    @Schema(hidden = true)
    private long id;

    @Schema(name = "cpf", description = "The cpf of the user", example = "42391552076")
    private String cpf;

    @Schema(name = "name", description = "The name of the user", example = "Pedro Moisés")
    private String name;

    @Schema(name = "userName", description = "The user name of the app", example = "pedro.gusmao")
    private String userName;

    @Schema(name = "password", description = "The password of the user", example = "megaSenha@123")
    private String password;

    @Schema(name = "email", description = "The email of the user", example = "pedro.pedro@mail.com")
    private String email;

    @Schema(name = "cellphone", description = "The phone of the user", example = "11400289220")
    private String cellphone;

    @Schema(name = "cartId", description = "The unique cart ID of the user", example = "1")
    private long cartId;
}
