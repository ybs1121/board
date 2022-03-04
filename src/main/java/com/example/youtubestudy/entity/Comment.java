package com.example.youtubestudy.entity;

import com.example.youtubestudy.dto.CommentDto;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Slf4j
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne // 해당 댓글 엔티티 여러개가, 하나의 Article에 연관된다
    @JoinColumn(name = "article_id")
    private Article article;

    @Column
    private String nickname;

    @Column
    private String body;

    public static Comment createComment(CommentDto dto, Article article) {
        // 예외 처리
        if(dto.getId() != null)
            throw new IllegalStateException("댓글 생성 실패 : id가 없음");


        if(dto.getArticleId() != article.getId()) {
            log.info("DTO: " +dto.toString());
            log.info("article: " +article.getId());

            throw new IllegalStateException("댓글 생성 실패 : 아이디가 다름");
        }

        // 엔티티 생성 및 반환
        return new Comment(
                dto.getId(),
                article,
                dto.getNickname(),
                dto.getBody()
        );
    }

    public void patch(CommentDto dto) {
        //예외 발생
        if(this.id != dto.getId())
            throw new IllegalStateException("댓글 수정 실패: 잘못된 id");

        //객체를 갱신
        if(dto.getNickname() != null)
            this.nickname = dto.getNickname();
        if(dto.getBody() != null)
            this.body = dto.getBody();


    }
}
