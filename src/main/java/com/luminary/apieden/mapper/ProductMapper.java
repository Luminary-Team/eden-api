package com.luminary.apieden.mapper;

import com.luminary.apieden.model.database.Product;
import com.luminary.apieden.model.request.ProductRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "usageTime", ignore = true)
    @Mapping(target = "conditionType", ignore = true)
    @Mapping(target = "user", ignore = true)
    Product toProduct(ProductRequest request);
}
