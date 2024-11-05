package com.luminary.apieden.mapper;

import com.luminary.apieden.model.client.ForumResponse;
import com.luminary.apieden.model.response.FindForumResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ForumMapper {
    @Mapping(source = "forumResponse.id", target = "id")
    @Mapping(source = "forumResponse.comments", target = "comments", ignore = true)
    FindForumResponse toFindForumResponse(ForumResponse forumResponse);
}
