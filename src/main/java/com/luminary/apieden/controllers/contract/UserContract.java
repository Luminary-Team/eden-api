package com.luminary.apieden.controllers.contract;

import com.luminary.apieden.models.database.User;
import com.luminary.apieden.models.request.TokenRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

@Tag(name = "User Controller", description = "Endpoints to interact with the UserEntity")
public interface UserContract {

    @Operation(description = "Create token to access authenticated endpoints")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Token created"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Access Denied")
    })
    public Map<String, String> login(final TokenRequest tokenRequest);

    @Operation(description = "Return all users registered in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All users are returned with success"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<User>> getUsers();

    @Operation(description = "Return an user by an unique attribute")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return user successful"),
            @ApiResponse(responseCode = "400", description = "Bad parameter passed"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @Parameter(name = "Authorization", description = "token given by '/login' ")
    @Parameter(name = "userId", example = "18",  description = "The id of the user")
    @Parameter(name = "cpf", example = "42391552076", description = "The Cpf of the user")
    @Parameter(name = "email", example = "pedro.pedro@mail.com", description = "The email of the user")
    public ResponseEntity<User> getUserById(String userId, String cpf, String email);
}
