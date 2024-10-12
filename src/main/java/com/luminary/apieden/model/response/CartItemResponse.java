package com.luminary.apieden.model.response;

import com.luminary.apieden.model.database.Product;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CartItemResponse {

    @NotNull(message = "O campo 'cart' precisa ser passado")
    @Schema(name = "cartId", description = "Unique ID of the cart in which a product will be inserted")
    private long cartId;

    @NotNull(message = "O campo 'product' precisa ser passado")
    @Schema(name = "product", description = "Unique ID of the product in which will be inserted into a cart")
    private Product product;
}
