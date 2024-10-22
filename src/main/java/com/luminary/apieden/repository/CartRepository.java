package com.luminary.apieden.repository;

import com.luminary.apieden.model.database.Cart;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUserId(long id);

    @Modifying
    @Transactional
    @Query(value = "CALL total_sale_calc(:cart_id)", nativeQuery = true)
    void totalSaleCalc(@Param("cart_id") int cartId);
}
