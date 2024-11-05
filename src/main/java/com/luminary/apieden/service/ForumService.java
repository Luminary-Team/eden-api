package com.luminary.apieden.service;

import com.luminary.apieden.client.ForumClient;
import com.luminary.apieden.mapper.ForumMapper;
import com.luminary.apieden.model.client.CommentResponse;
import com.luminary.apieden.model.client.ForumResponse;
import com.luminary.apieden.model.database.User;
import com.luminary.apieden.model.exception.HttpError;
import com.luminary.apieden.model.response.FindForumComment;
import com.luminary.apieden.model.response.FindForumResponse;
import com.luminary.apieden.repository.UserRepository;
import feign.FeignException;
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
                        findForumResponse.setUser(user);
                        return findForumResponse;
                    })
                    .toList();
        } else {
            ForumResponse forumResponse;
            try {
                forumResponse = forumClient.findById(id)
                        .stream().findFirst()
                        .orElse(null);
            } catch (FeignException.BadRequest feign) {
                throw new HttpError(HttpStatus.BAD_REQUEST, "Usuário não fez nenhum post.");
            }
            User user = userRepository.findById(forumResponse.getUserId())
                    .orElseThrow(() -> new HttpError(HttpStatus.INTERNAL_SERVER_ERROR, "Usuário não encontrado"));
            List<FindForumComment> comments = fetchingUsersOfComments(forumResponse.getComments());
            FindForumResponse findForumResponse = forumMapper.toFindForumResponse(forumResponse);
            findForumResponse.setUser(user);
            findForumResponse.setComments(comments);
            return List.of(findForumResponse);
        }
    }

    private List<FindForumComment> fetchingUsersOfComments(List<CommentResponse> comments) {
        if(comments != null) {
            return comments.stream()
                    .map(comment -> {
                        User user = userRepository.findById(comment.getUserId())
                                .orElseThrow(() -> new HttpError(HttpStatus.INTERNAL_SERVER_ERROR, "Usuário não encontrado"));
                        log.info("[FORUM] User {}", user);
                        return FindForumComment.builder()
                                .user(user)
                                .content(comment.getContent())
                                .build();
                    }).toList();
        }
        return List.of();
    }
}
