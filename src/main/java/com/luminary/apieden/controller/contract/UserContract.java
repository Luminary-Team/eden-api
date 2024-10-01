package com.luminary.apieden.controller.contract;

import com.luminary.apieden.model.database.User;
import com.luminary.apieden.model.response.ErrorResponse;
import com.luminary.apieden.model.request.TokenRequest;
import com.luminary.apieden.model.response.TokenResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

@Tag(name = "User Controller", description = "Endpoints to interact with the User entity, deprecated endpoints require token to be accessed")
public interface UserContract {

    @Operation(description = "Create token to access authenticated endpoints")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Token created"),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Access Denied",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public TokenResponse token(final TokenRequest tokenRequest);

    @Operation(summary = "Return all users(requires token)", description = "Return all users registered in the database(requires token)", deprecated = true)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All users are returned with success"),
            @ApiResponse(responseCode = "400", description = "No Bad Request error happens here",
                    content = @Content),
            @ApiResponse(responseCode = "418", description = "I'm a teapot"),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    public ResponseEntity<List<User>> findUsers();

    @Operation(summary = "Return an user by an unique attribute(requires token)", description = "Return an user by an unique attribute(requires token)", deprecated = true)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return user successful",
                    content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "400", description = "Bad parameter passed",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @Parameter(name = "Authorization", description = "token given by '/login' ")
    @Parameter(name = "userId", example = "18",  description = "The id of the user")
    @Parameter(name = "cpf", example = "42391552076", description = "The Cpf of the user")
    @Parameter(name = "email", example = "pedro.pedro@mail.com", description = "The email of the user")
    public ResponseEntity<User> findUserById(String userId, String cpf, String email);

    @Operation(summary = "Register user in database(requires token)", description = "Register an user in database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User registered successfully",
                    content = @Content(schema = @Schema(implementation = UserContract.class))),
            @ApiResponse(responseCode = "400", description = "Invalid attribute passed",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content)
    })
    public ResponseEntity<User> register(User userRequest);

    @Operation(summary = "Update user", description = "Update user based on the passed attributes", deprecated = true)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Updated user successfully",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid user to be created",
                    content = @Content(schema = @Schema(implementation = MapExample.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = @Content)
    })
    @RequestBody(content = @Content(schema = @Schema(implementation = MapExample.class)))
    public ResponseEntity<Map<String, Object>> partialUpdate(String id, Map<String, Object> request);

    @Operation(summary = "Delete user", description = "Delete user by id", deprecated = true)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User deleted with success"),
            @ApiResponse(responseCode = "400", description = "No Bad Request error happens here",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @Parameter(name = "id", example = "2", description = "The unique user id")
    public ResponseEntity<Void> deleteUserById(String id);
}
