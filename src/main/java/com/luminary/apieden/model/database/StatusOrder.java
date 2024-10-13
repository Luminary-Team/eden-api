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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "status_orders")
public class StatusOrder {
    @Id
    @Column(name = "pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(name = "id", description = "The id of the status order", example = "1")
    private long id;

    @Column(name = "status")
    @NotBlank(message = "O campo 'status' não pode ser vazio")
    @Size(message = "O campo 'status' não pode passar de 45 caracteres", max = 45)
    @Schema(name = "status", description = "The status of the order", example = "enviado")
    private String status;

    @Column(name = "description")
    @NotBlank(message = "O campo 'description' não pode ser vazio")
    @Size(message = "O campo 'description' não pode passar de 90 caracteres", max = 90)
    @Schema(name = "description", description = "The description of the status order", example = "Pedido enviado para entrega")
    private String description;
}
