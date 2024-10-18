package com.luminary.apieden.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class FindCartItemResponse {
    private List<CartItemResponse> cartItemResponseList;
    private float totalSale;
}
