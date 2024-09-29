package com.luminary.apieden.repository;

import com.luminary.apieden.model.database.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByProductId(long productId);
}
