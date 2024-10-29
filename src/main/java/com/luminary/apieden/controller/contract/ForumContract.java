package com.luminary.apieden.controller.contract;

import com.luminary.apieden.model.response.FindForumResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Forum Controller", description = "Endpoints to interact with Forum Entity")
public interface ForumContract {
    @Operation(summary = "Find forum", description = "Finds forum items and implements its respective users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All forums items returned successfully"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @Parameter(name = "id", description = "Id of the user")
    public ResponseEntity<List<FindForumResponse>> findAll(String id);
}
