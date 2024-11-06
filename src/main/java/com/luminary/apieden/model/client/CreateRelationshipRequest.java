package com.luminary.apieden.model.client;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
public class CreateRelationshipRequest {
    private long purchaserId;
    private long sellerId;
    private long productId;
}
