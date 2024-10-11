package com.luminary.apieden.repository;

import com.luminary.apieden.model.database.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentTypeRepository extends JpaRepository<PaymentType, Long> {
}
