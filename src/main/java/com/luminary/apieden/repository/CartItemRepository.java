package com.luminary.apieden.repository;

import com.luminary.apieden.model.database.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findCartItemsByCartId(long id);
}
