package com.luminary.apieden.controller.contract;

import com.luminary.apieden.model.database.Card;
import com.luminary.apieden.model.request.CardRequest;
import com.luminary.apieden.model.response.CardResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Card Controller", description = "Endpoints to interact with the Card entity")
public interface CardContract {
    @Operation(summary = "Find card by user id", description = "Find the user card based on the passed user id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request completed successfully"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content)
    })
    public ResponseEntity<List<CardResponse>> findByUserId(String id);

    @Operation(summary = "Register a card to a user", description = "Register an card to an user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Request completed successfully",
                    content = @Content(schema = @Schema(implementation = Card.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content)
    })
    public ResponseEntity<Card> register(CardRequest cardRequest);

    @Operation(summary = "Delete card by user id", description = "Delete a card based on his card id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Request completed successfully"),
            @ApiResponse(responseCode = "400", description = "Card not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content)
    })
    public ResponseEntity<Void> deleteCard(String id);
}
