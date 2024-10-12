package com.luminary.apieden.model.response;

import com.luminary.apieden.model.database.PaymentType;
import com.luminary.apieden.model.database.StatusOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class OrderResponse {
    private PaymentType paymentType;
    private StatusOrder statusOrder;
    private LocalDate orderDate;
    private String addressDelivery;
    private float totalSale;
}
