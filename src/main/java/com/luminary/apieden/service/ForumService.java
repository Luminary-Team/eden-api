package com.luminary.apieden.service;

import com.luminary.apieden.client.ForumClient;
import com.luminary.apieden.mapper.ForumMapper;
import com.luminary.apieden.model.client.ForumResponse;
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
                        return forumMapper.toFindForumResponse(
                                forum,
                                userRepository.findById(forum.getUserId())
                                        .orElseThrow(() ->
                                                new HttpError(HttpStatus.INTERNAL_SERVER_ERROR, "Usuário não encontrado")
                                        )
                        );
                    }).toList();
        } else {
            ForumResponse forumResponse = forumClient.findById(id);
            return List.of(forumMapper.toFindForumResponse(
                    forumResponse,
                    userRepository.findById(forumResponse.getUserId())
                            .orElseThrow(() -> new HttpError(HttpStatus.INTERNAL_SERVER_ERROR, "Usuário não encontrado"))
            ));
        }
    }
}
