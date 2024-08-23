package com.luminary.apieden.models.database;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.util.Date;

@Entity
@Table(name = "comments")
public class Comments {
    @Id
    @Column(name = "pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "fk_products_id")
    // Not Null
    private long productId;

    @Column(name = "fk_user_id")
    // Not Null
    private long userId;

    @Column(name = "content")
    // Not Null
    private String content;

    @Column(name = "creation_date")
    // Not Null
    private Date creationDate;
}
