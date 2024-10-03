package com.luminary.apieden.model.database;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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
    private long id;

    @Column(name = "rating")
    @Min(message = "O campo 'rating' não pode ser abaixo de 0", value = 0)
    private float rating;

    @OneToOne
    @JoinColumn(name = "fk_user_appraiser_id")
    @NotNull(message = "O campo 'userAppraiser' precisa ser passado")
    private User userAppraiser;

    @OneToOne
    @JoinColumn(name = "fk_products_id")
    private Product product;

    @Column(name = "fk_ecopoints_id")
    private int ecopointsId;

    @OneToOne
    @JoinColumn(name = "fk_users_evaluated_id")
    private User usersEvaluatedId;
}
