package com.luminary.apieden.model.database;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotBlank;
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
    @Schema(hidden = true)
    private long id;

    @Column(name = "fk_product_id")
    @NotNull(message = "O campo 'product' precisa ser passado")
    @Schema(name = "product", description = "The comment's respective product")
    private long productId;

    @Column(name = "fk_user_id")
    @NotNull(message = "O campo 'user' precisa ser passado")
    @Schema(name = "user", description = "The comment's respective user")
    private long userId;

    @Column(name = "comment")
    @NotBlank(message = "O campo 'comment' precisa ser passado")
    @Size(message = "O campo 'comment' não pode ultrapassar o limite 90 caracteres", max = 90)
    @Schema(name = "comment", description = "The respective comment", example = "Celular ultra potente")
    private String comment;
}
