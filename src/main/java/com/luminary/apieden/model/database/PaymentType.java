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
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "payment_types")
public class PaymentType {
    @Id
    @Column(name = "pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "type")
    @NotBlank(message = "O campo 'type' não pode ser vazio")
    @Size(message = "O campo 'type' não pode passar de 45 caracteres", max = 45)
    private String type;

    @Column(name = "description")
    @NotBlank(message = "O campo 'description' não pode ser vazio")
    @Size(message = "O campo 'description' não pode passar de 90 caracteres", max = 90)
    private String description;
}
