package com.luminary.apieden.mapper;

import com.luminary.apieden.model.database.Product;
import com.luminary.apieden.model.request.ProductRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "userId", ignore = true)
    Product toProduct(ProductRequest request);
}
