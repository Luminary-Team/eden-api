package com.luminary.apieden.controller;

import com.luminary.apieden.model.response.FindForumResponse;
import com.luminary.apieden.service.ForumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/forum")
@RequiredArgsConstructor
public class ForumController {
    private final ForumService forumService;
    @GetMapping
    public ResponseEntity<List<FindForumResponse>> findAll(@RequestParam(required = false) String id) {
        return ResponseEntity.status(HttpStatus.OK).body(forumService.find(id));
    }
}
