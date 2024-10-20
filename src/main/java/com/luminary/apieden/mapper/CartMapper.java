package com.luminary.apieden.mapper;

import com.luminary.apieden.model.database.CartItem;
import com.luminary.apieden.model.database.Product;
import com.luminary.apieden.model.response.CartItemResponse;
import com.luminary.apieden.model.response.FindCartItemResponse;
import com.luminary.apieden.model.response.RegisterCartItemResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CartMapper {
    @Mapping(source = "cartItem.id", target = "cartItemId")
    RegisterCartItemResponse toRegisterCartItemResponse(CartItem cartItem, Product product);

    @Mapping(source = "cartItem.id", target = "cartItemId")
    CartItemResponse toCartItemResponse(CartItem cartItem, Product product);

    @Mapping(source = "cartItemResponseList", target = "cartItems")
    FindCartItemResponse toFindCartItemResponse(List<CartItemResponse> cartItemResponseList, float totalSale);
}
