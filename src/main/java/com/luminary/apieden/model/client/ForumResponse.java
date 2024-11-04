package com.luminary.apieden.model.client;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ForumResponse {
    private long post_id;
    private long user_id;
    private String content;
    private List<Comment> comments;
    @Getter
    @Setter
    public static class Comment {
        private long post_id;
        private long user_d;
        private String content;
        private LocalDateTime post_date;
    }
    private LocalDateTime post_date;
}
