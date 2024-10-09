package com.luminary.apieden.controller.contract;

import com.luminary.apieden.model.database.Comment;
import com.luminary.apieden.model.request.CommentRequest;
import com.luminary.apieden.model.request.UpdateCommentRequest;
import com.luminary.apieden.model.response.CommentResponse;
import com.luminary.apieden.model.response.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Comment Controller", description = "Endpoints to interact with the Comment entity")
public interface CommentContract {
    @Operation(summary = "Return all comments by product id", description = "Returns all comments based on a product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All comments were returned successfully"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content)
    })
    @Parameter(name = "id", description = "Product id", example = "1")
    public ResponseEntity<List<CommentResponse>> findByProduct(String id);

    @Operation(summary = "Register comment", description = "Register a comment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Register a comment successfully",
                    content = @Content(schema = @Schema(implementation = Comment.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content)
    })
    public ResponseEntity<Comment> register(CommentRequest commentRequest);

    @Operation(summary = "Delete comment", description = "Delete comment by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Comment deleted successfully",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Comment not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content)
    })
    public ResponseEntity<Void> deleteComment(String id);
}
