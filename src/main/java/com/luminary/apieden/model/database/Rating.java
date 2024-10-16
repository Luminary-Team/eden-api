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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ratings")
public class Rating {
    @Id
    @Column(name = "pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "rating")
    @NotNull(message = "O campo 'rating' precisa ser passado")
    @Min(message = "O campo 'rating' não pode ser abaixo de 0", value = 0)
    @Schema(name = "rating", description = "The respective value of the rating", example = "3.5")
    private float rating;

    @Column(name = "fk_user_appraiser_id")
    @NotNull(message = "O campo 'userAppraiserId' precisa ser passado")
    @Schema(name = "userAppraiser", description = "The User appraiser id",example = "1")
    private long userAppraiserId;

    @Column(name = "fk_user_appraised_id")
    @NotNull(message = "O campo 'userAppraisedId' precisa ser passado")
    @Schema(name = "userEvaluated", description = "The userEvaluated information")
    private long userAppraisedId;
}
