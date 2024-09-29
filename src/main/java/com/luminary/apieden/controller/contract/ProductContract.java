package com.luminary.apieden.controller.contract;

import com.luminary.apieden.model.database.Product;
import com.luminary.apieden.model.request.ProductRequest;
import com.luminary.apieden.model.response.ErrorResponse;
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

@Tag(name = "Product Controller", description = "Endpoints to interact with Product Entity")
public interface ProductContract {
    @Operation(summary = "Find all products", description = "Find all products register in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All products were returned successfully"),
            @ApiResponse(responseCode = "400", description = "No Bad Request error happens here",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity<List<Product>> findProducts();

    @Operation(summary = "Find product by title", description = "Find all the products with the matching title")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All products matching title were returned successfully"),
            @ApiResponse(responseCode = "400", description = "No Bad Request error happens here",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity<List<Product>> findProductByTitleLike(String title);

    @Operation(summary = "Register product", description = "Register product in database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Product registered successfully"),
            @ApiResponse(responseCode = "400", description = "Product couldn't be register due to bad request",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<Product> register(ProductRequest productRequest);

    @Operation(summary = "Update product", description = "Product is updated based on the request sent by user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Product registered successfully",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid field passed",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @Parameter(name = "id", description = "Id of the product to be updated")
    @RequestBody(content = @Content(schema = @Schema(implementation = MapExample.class)))
    public ResponseEntity<Map<String, Object>> partialUpdate(String id, Map<String, Object> request);

    @Operation(summary = "Delete product", description = "Delete product by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Product deleted successfully",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Product was not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity<Void> deleteById(String id);
}
