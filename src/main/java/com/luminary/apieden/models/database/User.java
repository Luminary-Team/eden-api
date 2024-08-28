package com.luminary.apieden.models.database;

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

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "cpf", nullable = false, unique = true)
    @NotNull(message = "The 'cpf' field must be passed")
    @NotBlank(message = "The 'cpf' field mustn't be blank")
    @CPF(message = "The 'cpf' field is invalid")
    private String cpf;

    @Column(name = "name")
    @NotNull(message = "The 'name' field must be passed")
    @NotBlank(message = "The 'name' field mustn't be blank")
    private String name;

    @Column(name = "user_name")
    @NotNull(message = "The 'userName' field must be passed")
    @NotBlank(message = "The 'userName' field mustn't be blank")
    private String username;

    @Column(name = "password")
    @NotNull(message = "The 'password' field must be passed")
    @NotBlank(message = "The 'password' field mustn't be blank")
    private String password;

    @Column(name = "avaliation")
    private float avaliation;

    @Column(name = "email", nullable = false, unique = true)
    @NotNull(message = "The 'email' field must be passed")
    @NotBlank(message = "The 'email' field mustn't be blank")
    @Email(message = "The 'email' field is invalid")
    private String email;

    @Column(name = "phone", unique = true)
    @Size(message = "The 'phone' field must have 11 numbers", min = 11, max = 11)
    private String phone;

    @Column(name = "profile_image_url")
    private String profileImageUrl;

    @Column(name = "creation_date")
    @NotNull(message = "The 'creationDate' field must be passed")
    private LocalDate creationDate;

    @Column(name = "change_date")
    private LocalDate changeDate;
}
