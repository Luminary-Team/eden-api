package com.luminary.apieden.model.database;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ratings")
public class Rating {
    @Id
    @Column(name = "pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(hidden = true)
    private long id;

    @Column(name = "rating")
    @Min(message = "O campo 'rating' não pode ser abaixo de 0", value = 0)
    @Schema(name = "rating", description = "The respective value of the rating", example = "3.5")
    private float rating;

    @OneToOne
    @JoinColumn(name = "fk_user_appraiser_id")
    @NotNull(message = "O campo 'userAppraiser' precisa ser passado")
    @Schema(name = "userAppraiser", description = "The User appraiser information", implementation = User.class)
    private User userAppraiser;

    @OneToOne
    @JoinColumn(name = "fk_products_id")
    @Schema(name = "product", description = "The product information", implementation = Product.class)
    private Product product;

    @OneToOne
    @JoinColumn(name = "fk_ecopoints_id")
    @Schema(name = "ecopoint", description = "The ecopoint information", implementation = Ecopoint.class)
    private Ecopoint ecopoint;

    @OneToOne
    @JoinColumn(name = "fk_users_evaluated_id")
    @Schema(name = "ecopoint", description = "The ecopoint information", implementation = Ecopoint.class)
    private User userEvaluated;
}
