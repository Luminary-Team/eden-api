package com.luminary.apieden.model.response;

import com.luminary.apieden.model.database.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class FindForumResponse {
    private String id;
    private long postId;
    private User user;
    private String content;
    private List<FindForumComment> comments;
    private List<Long> engager;
    private LocalDateTime postDate;
}
