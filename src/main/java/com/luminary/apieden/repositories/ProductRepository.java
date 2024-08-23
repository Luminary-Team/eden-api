package com.luminary.apieden.repositories;

import com.luminary.apieden.models.database.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
