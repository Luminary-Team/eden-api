package com.luminary.apieden.model.database;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Ecopoint")
public class Ecopoint {
    @Id
    @Column(name = "pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(hidden = true)
    private long id;

    @Column(name = "name")
    @NotNull(message = "O campo 'name' precisa ser passado")
    @Schema(name = "name", description = "The name of the ecopoint", example = "até 6 meses")
    private String name;

    @Column(name = "address")
    @NotBlank(message = "O campo 'address' não pode ser vazio")
    @Schema(name = "address", description = "The address of the ecopoint", example = "Rua Irineu José Bordon, 335")
    private String address;

    @Column(name = "zip_code")
    @NotBlank(message = "O campo 'zip_code' não pode ser vazio")
    @Schema(name = "zipCode", description = "The zip code of the ecopoint", example = "Rua Irineu José Bordon, 335")
    private String zipCode;

    @Column(name = "avaliation")
    @NotNull(message = "O campo 'avaliation' precisa ser passado")
    @Schema(name = "avaliation", description = "The avaliation of the ecopoint")
    private float avaliation;
}
