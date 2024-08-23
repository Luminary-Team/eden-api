package com.luminary.apieden.models.database;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
@Entity
@Table(name = "products")
public class Product {
    @Id
    @Column(name = "pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "fk_condition_type_id")
    private long conditionTypeId;

    @Column(name = "fk_users_id")
    private int usersId;

    @Column(name = "name")
    private String name;

    @Column(name = "value")
    private String value;

    @Column(name = "description")
    private String description;

    @Column(name = "url_image")
    private String urlImage;

    @Column(name = "avaliation")
    private float avaliation;

    @Column(name = "stock")
    private int stock;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "change_date")
    private Date changeDate;
}
