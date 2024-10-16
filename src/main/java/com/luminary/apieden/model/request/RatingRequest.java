package com.luminary.apieden.model.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RatingRequest {
    @NotNull(message = "O campo 'userAppraiserId' precisa ser passado")
    private long userAppraiserId;

    @NotNull(message = "O campo 'userAppraisedId' precisa ser passado")
    private long userAppraisedId;

    @NotNull(message = "O campo 'rating' precisa ser passado")
    @Min(message = "O campo 'rating' não pode ser abaixo de 0", value = 0)
    private float rating;
}
