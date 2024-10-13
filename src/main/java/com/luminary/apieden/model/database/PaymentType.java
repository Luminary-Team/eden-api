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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payments_types")
public class PaymentType {
    @Id
    @Column(name = "pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(name = "id", description = "The id of the payment type", example = "1")
    private long id;

    @Column(name = "type")
    @NotBlank(message = "O campo 'type' não pode ser vazio")
    @Size(message = "O campo 'type' não pode passar de 45 caracteres", max = 45)
    @Schema(name = "type", description = "The type of the payment type", example = "pix")
    private String type;

    @Column(name = "description")
    @NotBlank(message = "O campo 'description' não pode ser vazio")
    @Size(message = "O campo 'description' não pode passar de 90 caracteres", max = 90)
    @Schema(name = "description", description = "Description of the respective payment type", example = "Transfrencia PIX")
    private String description;
}
