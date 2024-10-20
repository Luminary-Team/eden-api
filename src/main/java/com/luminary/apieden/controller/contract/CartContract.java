package com.luminary.apieden.controller.contract;

import com.luminary.apieden.model.request.CartItemRequest;
import com.luminary.apieden.model.response.FindCartItemResponse;
import com.luminary.apieden.model.response.RegisterCartItemResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Cart Controller", description = "Endpoints to interact with the Cart entity")
public interface CartContract {
    @Operation(summary = "Return all cart items by the cart id", description = "Return all cart items by the cart unique id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All comments were returned successfully"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content)
    })
    @Parameter(name = "cartId", description = "Cart id", example = "1")
    public ResponseEntity<FindCartItemResponse> findCartItems(String cartId);

    @Operation(summary = "Register an item in the cart", description = "Register an item based on the cart and product id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cart item registered with success"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content)
    })
    @Parameter(name = "cartId", description = "Cart id", example = "1")
    public ResponseEntity<RegisterCartItemResponse> register(CartItemRequest request);

    @Operation(summary = "Delete an item in the cart", description = "Delete a item in the cart based on his ID")
    @Parameter(name = "cartId", description = "Unique ID of the cart item, to be removed from the his respective cart")
    public ResponseEntity<Void> deleteCartItem(String cartItemId);
}
