package com.luminary.apieden.service;

import com.luminary.apieden.mapper.CommentMapper;
import com.luminary.apieden.model.database.Comment;
import com.luminary.apieden.model.database.Product;
import com.luminary.apieden.model.database.User;
import com.luminary.apieden.model.exception.HttpError;
import com.luminary.apieden.model.request.CommentRequest;
import com.luminary.apieden.model.request.UpdateCommentRequest;
import com.luminary.apieden.model.response.CommentResponse;
import com.luminary.apieden.repository.CommentRepository;
import com.luminary.apieden.repository.ProductRepository;
import com.luminary.apieden.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    public List<CommentResponse> findByProductId(String productId) {
        List<Comment> commentList = commentRepository.findByProductId(Long.parseLong(productId));
        return commentList.stream()
                .map(commentMapper::toCommentResponse)
                .toList();
    }
    public Comment register(CommentRequest commentRequest) {
        log.info("[COMMENT] Registering comment");
        log.info("[COMMENT] Finding product in database");
        Product product = productRepository.findById(commentRequest.getProductId())
                .orElseThrow(() -> new HttpError(HttpStatus.BAD_REQUEST, "Product not found"));
        log.info("[COMMENT] Found product");
        log.info("[COMMENT] Finding user in database");
        User user = userRepository.findById(commentRequest.getUserId())
                .orElseThrow(() -> new HttpError(HttpStatus.BAD_REQUEST, "User not found"));
        log.info("[COMMENT] Found user");
        log.info("[COMMENT] Prepared comment");
        Comment comment = commentMapper.toComment(commentRequest);
        comment.setProduct(product);
        comment.setUser(user);
        log.info("[COMMENT] Comment prepared");
        log.info("[COMMENT] Persisting comment in database");
        commentRepository.save(comment);
        log.info("[COMMENT] Comment persisted in database");
        return comment;
    }

    public CommentResponse updateComment(String id, UpdateCommentRequest updateCommentRequest) {
        Comment comment = commentRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new HttpError(HttpStatus.BAD_REQUEST, "Comment not found"));
        comment.setComment(updateCommentRequest.getComment());
        commentRepository.save(comment);
        return commentMapper.toCommentResponse(comment);
    }

    public void deleteComment(String id) {
        commentRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new HttpError(HttpStatus.BAD_REQUEST, "Comment not found"));
        commentRepository.deleteById(Long.valueOf(id));
    }
}
