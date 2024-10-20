package com.luminary.apieden.repository;

import com.luminary.apieden.model.database.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findProductsByPremium(boolean premium);
    List<Product> findByUserId(long id);
    List<Product> findByTitleLike(String title);
}
