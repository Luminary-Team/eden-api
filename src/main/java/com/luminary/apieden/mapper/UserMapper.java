package com.luminary.apieden.mapper;

import com.luminary.apieden.model.database.Cart;
import com.luminary.apieden.model.database.User;
import com.luminary.apieden.model.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "user.id", target = "id")
    @Mapping(source = "cart.id", target = "cartId")
    UserResponse toUserResponse(User user, Cart cart);
    UserResponse toUserResponse(User user);
}
