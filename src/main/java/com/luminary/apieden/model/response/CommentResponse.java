package com.luminary.apieden.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentResponse {
    private long id;
    private long productId;
    private long userId;
    private String comment;
}
