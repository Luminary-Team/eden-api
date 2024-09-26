package com.luminary.apieden.model.database;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usage_time")
public class UsageTime {
    @Id
    @Column(name = "pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(name = "id", description = "Usage time unique ID", example = "1")
    private long id;

    @Column(name = "time")
    @NotNull
    @Schema(name = "time", description = "How much time the row represents", example = "até 6 meses")
    private String time;

    @Column(name = "discount_percentage")
    @NotNull
    @Schema(name = "discountPercentage", description = "The respective discountPercentage corresponding to the usage time", example = "0.10")
    private float discountPercentage;
}
