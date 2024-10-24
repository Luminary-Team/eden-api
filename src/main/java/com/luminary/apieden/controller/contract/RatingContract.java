package com.luminary.apieden.controller.contract;

import com.luminary.apieden.model.database.Rating;
import com.luminary.apieden.model.request.RatingRequest;
import com.luminary.apieden.model.request.UpdateRatingRequest;
import com.luminary.apieden.model.response.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Rating Controller", description = "Endpoints to interact with the Rating entity")
public interface RatingContract {
    @Operation(summary = "Return rating", description = "Returns a rating based on the appraiser user id, and the appraised user id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rating entity is returned successfully"),
            @ApiResponse(responseCode = "500", description = "Internal Server")
    })
    @Parameter(name = "userAppraiserId", example = "1",  description = "The id of the appraiser user")
    @Parameter(name = "userAppraisedId", example = "2",  description = "The id of the appraised user")
    public ResponseEntity<Rating> getRating(String userAppraiserId, String userAppraisedId);

    @Operation(summary = "Register rating", description = "Register a rating")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rating entity is returned successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid field passed",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server")
    })
    public ResponseEntity<Rating> register(RatingRequest request);

    @Operation(summary = "Update rating", description = "Update a rating")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rating entity is updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid field passed",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server")
    })
    public ResponseEntity<Rating> update(
            String userAppraiserId,
            String userAppraisedId,
            UpdateRatingRequest request);
}
