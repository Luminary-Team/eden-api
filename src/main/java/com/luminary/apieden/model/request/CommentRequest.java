package com.luminary.apieden.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CommentRequest {
    @NotNull(message = "The 'productId' field must be passed")
    private long productId;

    @NotNull(message = "The 'userId' field must be passed")
    private long userId;

    @NotBlank(message = "The 'comment' field must be passed")
    private String comment;
}
