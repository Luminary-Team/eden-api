package com.luminary.apieden.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Schema(name = "RegisterOrderSchema", description = "The schema to register an order in database")
public class RegisterOrderRequest {
    @Schema(name = "cartId", description = "The id of the cart that the order will be based on", example = "1")
    @NotNull(message = "O campo 'cartId' precisa ser passado")
    private long cartId;

    @Schema(name = "paymentTypeId", description = "The id of the respective payment type chosen by the user", example = "1")
    @NotNull(message = "O campo 'paymentType' precisa ser passado")
    private long paymentTypeId;

    @Schema(name = "addressDelivery", description = "In what address is the order going to be delivered", example = "Rua Irineu José Bordon, 335")
    @NotBlank(message = "O campo 'addressDelivery' precisa ser passado")
    private String addressDelivery;
}
