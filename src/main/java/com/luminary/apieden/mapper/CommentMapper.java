package com.luminary.apieden.mapper;

import com.luminary.apieden.model.database.Comment;
import com.luminary.apieden.model.request.CommentRequest;
import com.luminary.apieden.model.response.CommentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    Comment toComment(CommentRequest commentRequest);
    CommentResponse toCommentResponse(Comment comment);
}
