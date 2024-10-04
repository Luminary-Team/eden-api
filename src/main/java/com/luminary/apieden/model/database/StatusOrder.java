package com.luminary.apieden.model.database;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "status_orders")
public class StatusOrder {
    @Id
    @Column(name = "pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(hidden = true)
    private long id;

    @Column(name = "status")
    @NotBlank(message = "O campo 'status' não pode ser vazio")
    private String status;

    @Column(name = "description")
    @NotBlank(message = "O campo 'description' não pode ser vazio")
    @Size(message = "O campo 'description' não pode passar de 45 caracteres", max = 45)
    private String description;
}
