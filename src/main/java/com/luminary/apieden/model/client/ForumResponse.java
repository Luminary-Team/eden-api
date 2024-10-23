package com.luminary.apieden.model.client;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ForumResponse {
    private String id;
    private long userId;
    private String content;
    private List<Comment> comments;
    private List<Long> likeId;

    @Getter
    @Setter
    public static class Comment {
        private long userId;
        private String content;
    }
}
