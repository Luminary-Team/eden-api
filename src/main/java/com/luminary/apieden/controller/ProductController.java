package com.luminary.apieden.controller;

import com.luminary.apieden.controller.contract.ProductContract;
import com.luminary.apieden.model.database.Product;
import com.luminary.apieden.model.request.ProductRequest;
import com.luminary.apieden.service.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController implements ProductContract {
    private final ProductService productService;

    @GetMapping("/getPremiumProducts")
    public ResponseEntity<List<Product>> getPremiumProducts(@RequestHeader String userId) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getPremiumProducts(userId));
    }

    @GetMapping("/getProducts")
    public ResponseEntity<List<Product>> getProducts(@RequestHeader String userId) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getNotPremiumProducts(userId));
    }

    @GetMapping("/getByUserId/{userId}")
    public ResponseEntity<List<Product>> getProductByUserId(@PathVariable String userId) {
        List<Product> productList = productService.findProductByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(productList);
    }

    @GetMapping("/getByTitle")
    public ResponseEntity<List<Product>> getProductByTitleLike(
            @RequestParam String userId,
            @RequestParam("title") String title) {
        List<Product> productList = productService.findProductByTitleLike(userId, title);
        return ResponseEntity.status(HttpStatus.OK).body(productList);
    }

    @GetMapping("/getByProductId/{productId}")
    public ResponseEntity<Product> getByProductId(@PathVariable String productId) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findProductById(productId));
    }

    @PostMapping("/register")
    public ResponseEntity<Product> register(@RequestBody @Valid ProductRequest productRequest) {
        Product product = productService.register(productRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Void> partialUpdate(@PathVariable String id, @RequestBody Map<String, Object> request) {
        productService.partialUpdate(id, request);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        productService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
