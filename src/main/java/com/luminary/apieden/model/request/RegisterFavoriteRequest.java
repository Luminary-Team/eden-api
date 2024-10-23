package com.luminary.apieden.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterFavoriteRequest {
    @Schema(name = "userId", description = "Unique ID of the user")
    private long userId;
    @Schema(name = "productId", description = "Unique ID of the product")
    private long productId;
}
