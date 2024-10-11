package com.luminary.apieden.mapper;

import com.luminary.apieden.model.database.Order;
import com.luminary.apieden.model.request.RegisterOrderRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order toOrder(RegisterOrderRequest request);
}
