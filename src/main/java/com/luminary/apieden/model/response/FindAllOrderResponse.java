package com.luminary.apieden.model.response;

import com.luminary.apieden.model.database.Product;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FindAllOrderResponse {
    @Schema(name = "productList", description = "List of the products purchased by the user")
    private List<Product> productList;

    public List<Product> add(Product product) {
        if (productList == null) {
            productList = new ArrayList<>();
        }
        productList.add(product);
        return this.productList;
    }
}
