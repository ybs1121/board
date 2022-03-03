package com.example.youtubestudt.repository;

import com.example.youtubestudt.entity.Article;
import com.example.youtubestudt.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest //JPA와 연동한 테스트
class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;


    @Test
    @DisplayName("특정 게시글의 모든 댓글 조회")
    void findByArticleId() {

        //case 1: 4번 게시글의 모든 댓글 조회
        {
            //입력 데이터 준비
            Long articleId = 4L;

            // 실제 수행
            List<Comment> commentList = commentRepository.findByArticleId(articleId);
            // 예상
            Article article = new Article(articleId,"음식","댓글");
            Comment a = new Comment(1L,article,"인간1","치킨");
            Comment b = new Comment(2L,article,"인간2","피자");
            Comment c = new Comment(3L,article,"인간3","치킨");

            List<Comment> expected = Arrays.asList(a,b,c);

            // 검증

            assertEquals(expected.toString(),commentList.toString(),"4반 글의 모든 댓글 예측측");
        }

        //case 2: 1번 게시글의 모든 댓글 조회
        {
            //입력 데이터 준비
            Long articleId = 1L;

            // 실제 수행
            List<Comment> commentList = commentRepository.findByArticleId(articleId);
            // 예상
            Article article = new Article(articleId,"ga","ga");
            List<Comment> expected = Arrays.asList();

            // 검증

            assertEquals(expected.toString(),commentList.toString(),"1번 글의 모든 댓글 예측측");
        }
    }
    @Test
    @DisplayName("특정 닉네임의 모든 댓글 조회")
    void findByNickname() {

        //case 1: 인간1 의 모든 댓글을 조회
        {
            // 입력 데이터 준비
            String name = "인간1";
            // 실제 수행
            List<Comment> commentList = commentRepository.findByNickname(name);
            // 예상
            Article article1 = new Article(4L,"음식","댓글");
            Article article2 = new Article(5L,"영화","좋아요");
            Article article3 = new Article(6L,"취미","구독");

            Comment a = new Comment(1L,article1,"인간1","치킨");
            Comment b = new Comment(4L,article2,"인간1","어바웃타임");
            Comment c = new Comment(7L,article3,"인간1","코딩");

            List<Comment> expected = Arrays.asList(a,b,c);



            // 검증

            assertEquals(expected.toString(),commentList.toString(),"닉네임: 인간1 로 조회");


        }
    }
}