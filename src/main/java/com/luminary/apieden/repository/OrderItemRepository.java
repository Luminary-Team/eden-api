package com.luminary.apieden.repository;

import com.luminary.apieden.model.database.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
