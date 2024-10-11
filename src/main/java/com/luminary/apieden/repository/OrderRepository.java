package com.luminary.apieden.repository;

import com.luminary.apieden.model.database.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
