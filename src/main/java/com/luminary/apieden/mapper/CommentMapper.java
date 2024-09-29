package com.luminary.apieden.mapper;

import com.luminary.apieden.model.database.Comment;
import com.luminary.apieden.model.request.CommentRequest;
import com.luminary.apieden.model.response.CommentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "user", ignore = true)
    Comment toComment(CommentRequest commentRequest);

    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "user.id", target = "userId")
    CommentResponse toCommentResponse(Comment comment);
}
