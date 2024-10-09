package com.luminary.apieden.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemRequest {
    @NotNull(message = "'cartId' precisa ser passado")
    @Schema(name = "cartId", description = "Unique ID of the cart in which a product will be inserted")
    private long cartId;

    @NotNull(message = "'productsId' precisa ser passado")
    @Schema(name = "productsId", description = "Unique ID of the product in which will be inserted into a cart")
    private long productsId;
}
