package com.luminary.apieden.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegisterOrderRequest {
    private long id;

    @NotNull(message = "O campo 'paymentType' precisa ser passado")
    private long paymentTypeId;

    @NotNull(message = "O campo 'statusOrderId' precisa ser passado")
    private long statusOrderId;

    @NotBlank(message = "O campo 'addressDelivery' precisa ser passado")
    private String addressDelivery;
}
