package com.luminary.apieden.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentResponse {
    @Schema(name = "id", description = "The comment's id")
    private long id;
    @Schema(name = "productId", description = "The comment's product id")
    private long productId;
    @Schema(name = "userId", description = "The comment's respective user id")
    private long userId;
    @Schema(name = "comment", description = "The comment")
    private String comment;
}
