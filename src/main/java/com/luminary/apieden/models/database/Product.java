package com.luminary.apieden.models.database;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @Column(name = "pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "fk_condition_type_id", nullable = false)
    @NotNull(message = "The 'conditionTypeId' field must be passed")
    private long conditionTypeId;

    @Column(name = "fk_users_id", nullable = false, unique = true)
    @NotNull(message = "The 'usersId' field must be passed")
    @NotBlank(message = "The 'usersId' field mustn't be blank")
    private long usersId;

    @Column(name = "name", nullable = false, unique = true)
    @NotNull(message = "The 'name' field must be passed")
    @NotBlank(message = "The 'name' field mustn't be blank")
    private String name;

    @Column(name = "value", nullable = false)
    @NotNull(message = "The 'value' field must be passed")
    private float value;

    @Column(name = "description", nullable = false)
    @NotNull(message = "The 'description' field must be passed")
    @NotBlank(message = "The 'description' field mustn't be blank")
    private String description;

    @Column(name = "url_image")
    private String urlImage;

    @Column(name = "avaliation")
    @NotNull(message = "The 'avaliation' field must be passed")
    private float avaliation;

    @Column(name = "stock")
    @NotNull(message = "The 'stock' field must be passed")
    private int stock;
}
