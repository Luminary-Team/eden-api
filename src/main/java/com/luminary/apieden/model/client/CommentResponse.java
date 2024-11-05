package com.luminary.apieden.model.client;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentResponse {
    private long userId;
    private String content;
    private LocalDateTime postDate;
}
