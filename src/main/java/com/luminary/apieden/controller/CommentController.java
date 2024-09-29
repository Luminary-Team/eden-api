package com.luminary.apieden.controller;

import com.luminary.apieden.controller.contract.CommentContract;
import com.luminary.apieden.model.database.Comment;
import com.luminary.apieden.model.request.CommentRequest;
import com.luminary.apieden.model.request.UpdateCommentRequest;
import com.luminary.apieden.model.response.CommentResponse;
import com.luminary.apieden.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController implements CommentContract {
    private final CommentService commentService;

    @GetMapping("/product/{id}")
    public ResponseEntity<List<CommentResponse>> findByProduct(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.findByProductId(id));
    }

    @PostMapping("/")
    public ResponseEntity<Comment> register(@RequestBody @Valid CommentRequest commentRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.register(commentRequest));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CommentResponse> updateComment(
            @PathVariable String id,
            @RequestBody @Valid UpdateCommentRequest updateCommentRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.updateComment(id, updateCommentRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable String id) {
        commentService.deleteComment(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
}
