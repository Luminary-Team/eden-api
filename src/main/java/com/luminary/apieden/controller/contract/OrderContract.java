package com.luminary.apieden.controller.contract;

import com.luminary.apieden.model.request.RegisterOrderRequest;
import com.luminary.apieden.model.response.ErrorResponse;
import com.luminary.apieden.model.response.FindAllOrderResponse;
import com.luminary.apieden.model.response.OrderResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Order Controller", description = "Endpoints to interact with the Order entity")
public interface OrderContract {
    @Operation(summary = "Registers an order", description = "Register an order based on a cart id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Order Registered successfully",
                    content = @Content(schema = @Schema(implementation = OrderResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<OrderResponse> register(RegisterOrderRequest request);

    @Operation(summary = "Get all users by user", description = "Get all orders made by the user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Orders items returned successfully",
                    content = @Content(schema = @Schema(implementation = FindAllOrderResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @Parameter(name = "userId", description = "The id of the user")
    public ResponseEntity<FindAllOrderResponse> getAll(String userId);
}
