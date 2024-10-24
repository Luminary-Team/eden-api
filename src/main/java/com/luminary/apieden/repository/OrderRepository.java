package com.luminary.apieden.repository;

import com.luminary.apieden.model.database.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findOrderByUserId(long userId);
}
