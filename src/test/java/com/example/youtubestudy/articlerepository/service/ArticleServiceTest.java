package com.example.youtubestudy.articlerepository.service;

import com.example.youtubestudy.dto.ArticleForm;
import com.example.youtubestudy.entity.Article;
import com.example.youtubestudy.service.ArticleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest //해당 클래스는 스프링 부트와 연동되어 테스팅 된다
class ArticleServiceTest {


    @Autowired
    ArticleService articleService;

    @Test
    void index() {
        //예상 시나리오
        Article a = new Article(1L,"ga","ga");
        Article b = new Article(2L,"na","na");
        Article c = new Article(3L,"da","da");

        List<Article> expected = new ArrayList<>(Arrays.asList(a,b,c));

        //실제
        List<Article> articleList = articleService.index();
        //비교

        assertEquals(expected.toString(),articleList.toString());
    }

    @Test
    void show_성공________존재하는_id_입력() {
        Long id = 1L;
        Article expected = new Article(1L,"ga","ga");

        Article article = articleService.show(id);

        assertEquals(expected.toString(),article.toString());
    }

    @Test

    void show_실패_____존재하지않은_id_입력() {
        Long id = -1L;
        Article expected = null;

        Article article = articleService.show(id);

        assertEquals(expected,article);
    }

    @Test
    @Transactional
    void create__성공__title과_content만_있는_dto_입력() {
        String title = "라라라";
        String content = "444";
        ArticleForm dto = new ArticleForm(title,content,null);
        Article expected = new Article(4L,title,content);



        Article article = articleService.create(dto);

        assertEquals(expected.toString(),article.toString());
    }

    @Test
    void create_실패__아이디가_있는_dto_입력() {


        String title = "라라라";
        String content = "444";
        ArticleForm dto = new ArticleForm(title,content,4L);
        Article expected = null;



        Article article = articleService.create(dto);

        assertEquals(expected,article);
    }
}