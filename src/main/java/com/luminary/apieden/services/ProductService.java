package com.luminary.apieden.services;

import com.luminary.apieden.models.database.Product;
import com.luminary.apieden.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    public final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(String id) {
        return productRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public Product register(Product product) {
        return productRepository.save(product);
    }
}
