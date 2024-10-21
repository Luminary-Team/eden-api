package com.luminary.apieden.model.database;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @Column(name = "pk_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "fk_payment_type_id")
    @NotNull(message = "O campo 'paymentType' precisa ser passado")
    private long paymentTypeId;

    @Column(name = "fk_status_order_id")
    @NotNull(message = "O campo 'statusOrderId' precisa ser passado")
    private long statusOrderId;

    @Column(name = "fk_user_id")
    @NotNull(message = "O campo 'userId' precisa ser passado")
    private long userId;

    @Column(name = "order_date")
    @NotNull(message = "O campo 'orderDate' precisa ser passado")
    private LocalDate orderDate;

    @Column(name = "address_delivery")
    @NotBlank(message = "O campo 'addressDelivery' precisa ser passado")
    private String addressDelivery;

    @Column(name = "total_sale")
    @NotNull(message = "O campo 'totalSale' precisa ser passado")
    private float totalSale;
}
