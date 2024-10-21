package com.luminary.apieden.model.response;

import com.luminary.apieden.model.database.PaymentType;
import com.luminary.apieden.model.database.StatusOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class OrderResponse {
    @Schema(name = "userId", description = "The id of the user completing the order", example = "1")
    private long userId;
    @Schema(name = "paymentType", description = "The user payment type selected by the user", implementation = PaymentType.class)
    private PaymentType paymentType;
    @Schema(name = "statusOrder", description = "The status order of the order", implementation = StatusOrder.class)
    private StatusOrder statusOrder;
    @Schema(name = "orderDate", description = "The date when the order was made", example = "04/18")
    private LocalDate orderDate;
    @Schema(name = "addressDelivery", description = "The respective address of where the order is going to be delivered", example = "Rua Irineu José Bordon, 335")
    private String addressDelivery;
    @Schema(name = "totalSale", description = "The total amount of the order", example = "20005.0")
    private float totalSale;
}
