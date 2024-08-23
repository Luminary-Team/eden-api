package com.luminary.apieden.models.database;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.util.Date;

@Entity
@Table(name = "usage_time")
public class UsageTime {
    @Id
    @Column(name = "pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "time")
    // Not Null
    private String time;

    @Column(name = "discount_percentage")
    // Not Null
    private float discount_percentage;

    @Column(name = "creation_date")
    // Not Null
    private Date creationDate;

    @Column(name = "change_date")
    private Date changeDate;
}
