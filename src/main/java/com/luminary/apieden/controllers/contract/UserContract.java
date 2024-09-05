package com.luminary.apieden.controllers.contract;

import com.luminary.apieden.models.database.User;
import com.luminary.apieden.models.request.RegisterProfileImageRequest;
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

@Tag(name = "User Controller", description = "Endpoints to interact with the UserEntity")
public interface UserContract {
    @Operation(description = "Return a user based on his ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The user is returned with success",
                    content = @Content(schema = @Schema(implementation = User.class))
            ),
            @ApiResponse(responseCode = "400", description = "The ID is not registered in the database",
                    content = @Content(schema = @Schema(type = "application/json", example = "\"cpf\": \"'cpf' isn't valid\""))
            ),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @Parameter(name = "id", example = "18", description = "The id of the user")
    public ResponseEntity<User> getUserById(String id);

    @Operation(description = "Return all users registered in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All users are returned with success"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<User>> getUsers();

    @Operation(description = "Register a user in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "The user is registered with success"),
            @ApiResponse(responseCode = "400", description = "Invalid field passed",
                    content = @Content(schema = @Schema(type = "application/json", example = "\"cpf\": \"'cpf' isn't valid\""))),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<User> register(User userRequest);
    @Operation(description = "Update the passed fields on the body")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204"),
            @ApiResponse(responseCode = "400", description = "The id does not exists",
                content = @Content(schema = @Schema(implementation = String.class)))
    })
    public ResponseEntity partialUpdate(String id, Map<String, Object> request);

}
