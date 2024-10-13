package com.luminary.apieden.mapper;
import com.luminary.apieden.model.database.Order;
import com.luminary.apieden.model.database.PaymentType;
import com.luminary.apieden.model.database.StatusOrder;
import com.luminary.apieden.model.request.RegisterOrderRequest;
import com.luminary.apieden.model.response.OrderResponse;
import org.mapstruct.Mapper;

import java.time.LocalDate;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order toOrder(RegisterOrderRequest request, long statusOrderId, LocalDate orderDate);
    OrderResponse toOrderResponse(Order order, StatusOrder statusOrder, PaymentType paymentType, float totalSale);
}
