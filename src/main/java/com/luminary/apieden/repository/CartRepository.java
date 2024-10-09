package com.luminary.apieden.repository;

import com.luminary.apieden.model.database.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUserId(long id);
}
