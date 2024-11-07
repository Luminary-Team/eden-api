package com.luminary.apieden.model.client;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ForumResponse {
    private String id;
    private long postId;
    private long userId;
    private String content;
    private List<CommentResponse> comments;
    private LocalDateTime postDate;
    private List<Long> engager;
}
