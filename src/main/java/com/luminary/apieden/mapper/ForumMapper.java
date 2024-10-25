package com.luminary.apieden.mapper;

import com.luminary.apieden.model.client.ForumResponse;
import com.luminary.apieden.model.database.User;
import com.luminary.apieden.model.response.FindForumResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ForumMapper {
    @Mapping(source = "forumResponse.id", target = "id")
    FindForumResponse toFindForumResponse(ForumResponse forumResponse);
}
