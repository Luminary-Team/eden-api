package com.luminary.apieden.mapper;

import com.luminary.apieden.model.database.Product;
import com.luminary.apieden.model.database.User;
import com.luminary.apieden.model.request.ProductRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "user", target = "user")
    Product toProduct(ProductRequest request, User user);
}
