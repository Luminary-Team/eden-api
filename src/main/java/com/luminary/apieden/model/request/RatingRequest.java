package com.luminary.apieden.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RatingRequest {
    @NotNull(message = "O campo 'userAppraiserId' precisa ser passado")
    @Schema(name = "userAppraiserId", description = "ID of the appraiser user", example = "1")
    private long userAppraiserId;

    @NotNull(message = "O campo 'userAppraisedId' precisa ser passado")
    @Schema(name = "userAppraisedId", description = "ID of the appraised user", example = "2")
    private long userAppraisedId;

    @NotNull(message = "O campo 'rating' precisa ser passado")
    @Min(message = "O campo 'rating' não pode ser abaixo de 0", value = 0)
    @Schema(name = "rating", description = "The rating of the user", example = "1.5")
    private float rating;
}
