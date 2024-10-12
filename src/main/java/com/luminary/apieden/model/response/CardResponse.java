package com.luminary.apieden.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "CardResponseSchema", description = "The user response schema")
public class CardResponse {
    @NotNull(message = "The 'id' field must be passed")
    @Schema(name = "id", description = "The card's id")
    private long id;
    @NotNull(message = "The 'userId' field must be passed")
    @Schema(name = "userId", description = "The card's respective user id")
    private long userId;
    @NotBlank(message = "O campo 'cardNumber' precisa ser passado")
    @Schema(name = "cardNumber", description = "The card's number")
    private String cardNumber;
    @NotBlank(message = "O campo 'validity' precisa ser passado")
    @Schema(name = "validity", description = "The card's validity")
    private String validity;
}
