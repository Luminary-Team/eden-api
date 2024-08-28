package com.luminary.apieden.models.database;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "condition_types")
public class ConditionTypes {
    @Id
    @Column(name = "pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "fk_usage_time_id")
    private long idUsageTime;

    @Column(name = "name")
    // Not NULL
    private String name;

    @Column(name = "description")
    // Not Null
    private String description;

    @Column(name = "creation_date")
    // Not Null
    private LocalDate creationDate;

    @Column(name = "change_date")
    private LocalDate changeDate;
}
