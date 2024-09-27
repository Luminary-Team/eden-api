package com.luminary.apieden.controller;

import com.luminary.apieden.controller.contract.ProductContract;
import com.luminary.apieden.model.database.Product;
import com.luminary.apieden.model.exception.HttpError;
import com.luminary.apieden.model.request.ProductRequest;
import com.luminary.apieden.model.response.ErrorResponse;
import com.luminary.apieden.service.ProductService;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@Slf4j
public class ProductController implements ProductContract {
    private final ProductService productService;

    @GetMapping("/findAll")
    public ResponseEntity<List<Product>> findProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findAll());
    }

    @GetMapping("/findByTitle")
    public ResponseEntity<List<Product>> findProductByTitleLike(
            @RequestParam("title") String title) {
        List<Product> productList = productService.findProductByTitleLike(title);
        return ResponseEntity.status(HttpStatus.OK).body(productList);
    }

    @PostMapping("/register")
    public ResponseEntity<Product> register(@RequestBody @Valid ProductRequest productRequest) {
        log.info("Registering product");
        Product product = productService.register(productRequest);
        log.info("Product registered with success: {}", product);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> partialUpdate(@PathVariable String id, @RequestBody Map<String, Object> request) {
        log.info("Entering in partialUpdate method.");
        productService.partialUpdate(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(request);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        log.info("Deleting product {}", id);
        productService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, Object> verifyProduct(BindingResult result) {
        Map<String, Object> errors = new HashMap<>();
        for (FieldError error: result.getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return errors;
    }

    @ExceptionHandler(HttpError.class)
    public ResponseEntity<ErrorResponse> genericHandler(HttpError error) {
        log.error("An error occurred. {}", error.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(error.getStatus(), error.getMessage());
        return ResponseEntity.status(errorResponse.getHttpStatus()).body(errorResponse);
    }
}
