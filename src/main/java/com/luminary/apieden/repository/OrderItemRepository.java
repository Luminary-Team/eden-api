package com.luminary.apieden.repository;

import com.luminary.apieden.model.database.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findOrderItemsByOrderId(long orderId);
}
