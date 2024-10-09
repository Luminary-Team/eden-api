package com.luminary.apieden.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class RegisterOrderRequest {
    private long id;

    @NotNull(message = "O campo 'paymentType' precisa ser passado")
    private long paymentType;

    @NotNull(message = "O campo 'statusOrderId' precisa ser passado")
    private long statusOrderId;

    @NotNull(message = "O campo 'orderDate' precisa ser passado")
    private LocalDate orderDate;

    @NotBlank(message = "O campo 'addressDelivery' precisa ser passado")
    private String addressDelivery;

    @NotNull(message = "O campo 'totalSale' precisa ser passado")
    private float totalSale;
}
