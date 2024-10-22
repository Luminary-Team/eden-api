package com.luminary.apieden.repository;

import com.luminary.apieden.model.database.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByUserId(long id);
    List<Product> findByTitleLike(String title);
    List<Product> findProductsByUserIdNotAndPremiumIsTrue(long userId);
    List<Product> findProductsByUserIdNotAndPremiumIsFalse(long userId);
}
