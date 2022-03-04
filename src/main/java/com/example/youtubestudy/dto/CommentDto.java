package com.example.youtubestudy.dto;

import com.example.youtubestudy.entity.Comment;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Slf4j
public class CommentDto {
    private Long id;
    @JsonProperty("article_id")
    private Long articleId;
    private String nickname;
    private String body;

    public static CommentDto createCommentDto(Comment comment) {
        CommentDto test = new CommentDto(comment.getId(),
                comment.getArticle().getId(),
                comment.getNickname(),
                comment.getBody());
        log.info(test.toString());


        return new CommentDto(
                comment.getId(),
                comment.getArticle().getId(),
                comment.getNickname(),
                comment.getBody());
    }
}
