package com.luminary.apieden.model.database;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "avaliation")
public class Avaliation {
    @Id
    @Column(name = "pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "avaliation")
    private float avaliation;

    @Column(name = "fk_user_appraiser_id")
    private int userAppraiserId;

    @Column(name = "fk_products_id")
    private int productsId;

    @Column(name = "fk_ecopoints_id")
    private int ecopointsId;

    @Column(name = "fk_users_evaluated_id")
    private int usersEvaluatedId;
}