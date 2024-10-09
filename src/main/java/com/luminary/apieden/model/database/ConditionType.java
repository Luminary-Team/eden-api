package com.luminary.apieden.model.database;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "condition_types")
public class ConditionType {
    @Id
    @Column(name = "pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(name = "id", description = "Usage time unique ID", example = "1")
    private long id;

    @Column(name = "type")
    @NotBlank(message = "O campo 'type' não pode ser vazio")
    @Schema(name = "type", description = "What type does the respective ID corresponds to", example = "novo")
    private String type;

    @Column(name = "description")
    @NotBlank(message = "O campo 'description' não pode ser vazio")
    @Schema(name = "description", description = "What is the condition type description", example = "Aparelho em ótimo estado")
    private String description;
}