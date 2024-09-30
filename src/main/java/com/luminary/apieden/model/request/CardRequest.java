package com.luminary.apieden.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CardRequest {

    @NotNull(message = "The 'userId' field must be passed")
    private long userId;

    @NotNull(message = "The 'cardNumber' field must be passed")
    private String cardNumber;

    @NotNull(message = "The 'cvv' field must be passed")
    private String cvv;

    @NotNull(message = "The 'cvc' field must be passed")
    private String cvc;

    @NotNull(message = "The 'validity' field must be passed")
    private String validity;

}
