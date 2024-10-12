package com.luminary.apieden.mapper;

import com.luminary.apieden.model.database.CartItem;
import com.luminary.apieden.model.database.Product;
import com.luminary.apieden.model.response.CartItemResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartMapper {
    CartItemResponse toRegisterCartItem(CartItem cartItem, Product product);
}
