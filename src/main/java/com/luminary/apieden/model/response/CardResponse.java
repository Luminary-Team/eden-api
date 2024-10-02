package com.luminary.apieden.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardResponse {

    @Schema(name = "id", description = "The card's id")
    private long id;
    @Schema(name = "userId", description = "The card's respective user id")
    private long userId;
    @Schema(name = "cardNumber", description = "The card's number")
    private String cardNumber;
    @Schema(name = "validity", description = "The card's validity")
    private String validity;
}
