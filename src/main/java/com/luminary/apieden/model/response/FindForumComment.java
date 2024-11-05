package com.luminary.apieden.model.response;

import com.luminary.apieden.model.database.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class FindForumComment {
    private User user;
    private String content;
}
