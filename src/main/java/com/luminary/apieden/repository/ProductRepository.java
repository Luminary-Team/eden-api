package com.luminary.apieden.repository;

import com.luminary.apieden.model.database.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
