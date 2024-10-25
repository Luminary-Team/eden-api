package com.luminary.apieden.service;

import com.luminary.apieden.client.ForumClient;
import com.luminary.apieden.mapper.ForumMapper;
import com.luminary.apieden.model.client.ForumResponse;
import com.luminary.apieden.model.database.User;
import com.luminary.apieden.model.exception.HttpError;
import com.luminary.apieden.model.response.FindForumResponse;
import com.luminary.apieden.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ForumService {
    private final UserRepository userRepository;
    private final ForumMapper forumMapper;
    private final ForumClient forumClient;

    public List<FindForumResponse> find(String id) {
        if (id == null) {
            List<ForumResponse> forumList = forumClient.findAll();
            return forumList.stream()
                    .map(forum -> {
                        FindForumResponse findForumResponse = forumMapper.toFindForumResponse(forum);
                        User user = userRepository.findById(forum.getUserId())
                                        .orElseThrow(() ->
                                                new HttpError(HttpStatus.INTERNAL_SERVER_ERROR, "Usuário não encontrado")
                                        );
                        List<FindForumResponse.FindForumComment> findForumComment = fetchingUsersOfComments(forum.getComments());
                        findForumResponse.setUser(user);
                        findForumResponse.setComments(findForumComment);
                        return findForumResponse;
                    })
                    .toList();
        } else {
            ForumResponse forumResponse = forumClient.findById(id);
            List<FindForumResponse.FindForumComment> comments = fetchingUsersOfComments(forumResponse.getComments());
            User user = userRepository.findById(forumResponse.getUserId())
                    .orElseThrow(() -> new HttpError(HttpStatus.INTERNAL_SERVER_ERROR, "Usuário não encontrado"));
            FindForumResponse findForumResponse = forumMapper.toFindForumResponse(forumResponse);
            findForumResponse.setUser(user);
            findForumResponse.setComments(comments);
            return List.of(findForumResponse);
        }
    }

    private List<FindForumResponse.FindForumComment> fetchingUsersOfComments(List<ForumResponse.Comment> comments) {
        return comments.stream()
                .map(comment -> {
                    User user = userRepository.findById(comment.getUserId())
                            .orElseThrow(() -> new HttpError(HttpStatus.INTERNAL_SERVER_ERROR, "Usuário não encontrado"));
                    log.info("[FORUM] User {}", user);
                    FindForumResponse.FindForumComment findForumComment = FindForumResponse.FindForumComment.builder()
                            .user(user)
                            .content(comment.getContent())
                            .build();
                    return findForumComment;
                }).toList();
    }
}
