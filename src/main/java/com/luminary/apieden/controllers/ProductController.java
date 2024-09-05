package com.luminary.apieden.controllers;

import com.luminary.apieden.models.database.Product;
import com.luminary.apieden.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    public final ProductService productService;
    @GetMapping("/")
    public ResponseEntity<List<Product>> getUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getUserById(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProductById(id));
    }

    @PostMapping("/register-product")
    public ResponseEntity<Product> registerProduct(@RequestBody @Valid Product product) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.registerProduct(product));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, Object> verifyUser(BindingResult result) {
        Map<String, Object> errors = new HashMap<>();
        for (FieldError error: result.getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return errors;
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public String loginBadRequest(RuntimeException exception) {
        return exception.getMessage();
    }
}
