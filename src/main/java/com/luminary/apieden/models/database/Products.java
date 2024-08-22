package com.luminary.apieden.models.database;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
@Entity
@Table(name = "products")
public class Products {
    @Id
    @Column(name = "pk_id")
    private int id;

    @Column(name = "fk_condition_type_id")
    private int conditionTypeId;

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
