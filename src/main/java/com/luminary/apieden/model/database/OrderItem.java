package com.luminary.apieden.model.database;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItem {
    @Column(name = "pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "fk_products_id")
    @NotNull(message = "O campo 'productId' precisa ser passado")
    private long productId;

    @Column(name = "fk_orders_id")
    @NotNull(message = "O campo 'orderId' precisa ser passado")
    private long orderId;
}
