package com.luminary.apieden.models.database;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;
import org.hibernate.validator.constraints.br.CPF;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "users")
@Schema(name = "UserSchema", description = "Schema of users")
public class User {

    @Id
    @Column(name = "pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "cpf", nullable = false, unique = true)
    @NotNull(message = "The 'cpf' field must be passed")
    @NotBlank(message = "The 'cpf' field mustn't be blank")
    @CPF(message = "The 'cpf' field is invalid")
    @Schema(name = "cpf", description = "The cpf of the user", example = "42391552076")
    private String cpf;

    @Column(name = "name")
    @NotNull(message = "The 'name' field must be passed")
    @NotBlank(message = "The 'name' field mustn't be blank")
    @Schema(name = "name", description = "The name of the user", example = "Pedro Moisés")
    private String name;

    @Column(name = "user_name")
<<<<<<< HEAD
    @NotNull(message = "The 'userName' field must be passed")
    @NotBlank(message = "The 'userName' field mustn't be blank")
    private String username;
=======
    @NotBlank(message = "The 'userName' must be passed")
    @Schema(name = "userName", description = "The user name of the app", example = "pedro.gusmao")
    private String userName;
>>>>>>> c45007a8af9b711b71277e8e4a18026f36255ad6

    @Column(name = "password")
    @NotBlank(message = "The 'password' field must be passed")
    @Schema(name = "password", description = "The password of the user", example = "megaSenha@123")
    private String password;

    @Column(name = "avaliation")
    @Schema(name = "avaliation", description = "The avaliation of the user", example = "3.5")
    private float avaliation;

    @Column(name = "email", nullable = false, unique = true)
    @NotBlank(message = "The 'email' field must be passed")
    @Email(message = "The 'email' field is invalid")
    @Schema(name = "email", description = "The email of the user", example = "pedro.pedro@mail.com")
    private String email;

    @Column(name = "phone", unique = true)
    @Size(message = "The 'phone' field must have 11 numbers", min = 11, max = 11)
    @Schema(name = "phone", description = "The phone of the user", example = "40028922")
    private String phone;

    @Column(name = "profile_image_url")
    @Schema(name = "profileImageUrl", description = "The profile url of the user", example = "http.cat/")
    private String profileImageUrl;
}
