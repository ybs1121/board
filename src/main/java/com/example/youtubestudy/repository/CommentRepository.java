package com.example.youtubestudy.repository;

import com.example.youtubestudy.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    @Query(value = "SELECT * FROM COMMENT  WHERE article_id = :articleId",
    nativeQuery = true)
    List<Comment> findByArticleId(Long articleId);

    List<Comment>findByNickname(String nickname);
}
