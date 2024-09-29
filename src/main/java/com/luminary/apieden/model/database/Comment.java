package com.luminary.apieden.model.database;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @Column(name = "pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "fk_product_id", nullable = false)
    @NotNull(message = "The 'product' field must be passed")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "fk_user_id")
    @NotNull(message = "The 'user' field must be passed")
    private User user;

    @Column(name = "comment")
    @NotNull(message = "The 'comment' field mustn't be passed")
    @Size(message = "The 'comment' must have not pass the 90 digits limit", max = 90)
    private String comment;
}
