package com.luminary.apieden.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
@Schema(name = "CardRequestSchema", description = "Schema of cards request")
public class CardRequest {

    @NotNull(message = "The 'userId' field must be passed")
    @Schema(description = "The respective ID of the user owner of this card", example = "6")
    private long userId;

    @NotNull(message = "The 'cardNumber' field must be passed")
    @Schema(name = "cardNumber", description = "The cardNumber of the card", example = "1111222233334444")
    private String cardNumber;

    @NotNull(message = "The 'cvv' field must be passed")
    @Schema(name = "cvv", description = "The cvv of the card", example = "123")
    private String cvv;

    @NotNull(message = "The 'cvc' field must be passed")
    @Schema(hidden = true)
    private String cvc;

    @NotNull(message = "The 'validity' field must be passed")
    @Schema(name = "validity", description = "The validity of the card", example = "01/27")
    private String validity;

}
