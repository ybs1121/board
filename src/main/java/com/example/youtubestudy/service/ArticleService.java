package com.example.youtubestudy.service;

import com.example.youtubestudy.repository.ArticleRepository;
import com.example.youtubestudy.dto.ArticleForm;
import com.example.youtubestudy.entity.Article;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service // 서비스 선언! (서비스 객체를 스프링 부트에 선언)
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> index() {
        return articleRepository.findAll();
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }


    public Article create(ArticleForm dto) {
        Article article = dto.toEntity();
        if(article.getId() != null){
            return null;
        }
        return articleRepository.save(article);
    }

    public Article update(Long id, ArticleForm dto) {
        Article article = dto.toEntity();
        log.info(id + article.toString());

        Article target = articleRepository.findById(id).orElse(null);

        if(target == null || id != article.getId()){
            log.info("잘못된 요청 발생");
            return null;
        }

        target.patch(article);
        Article updated = articleRepository.save(target);
        return updated;

    }

    public Article delete(Long id) {
        Article deleted = articleRepository.findById(id).orElse(null);

        if(deleted == null){
            return null;
        }

        return deleted;
    }

    @Transactional
    public List<Article> createArticle(List<ArticleForm> dtos) {
        //dto 묶음을 entity 묶음으로 변환\
        List<Article> articleList = dtos.stream()
                .map(dto -> dto.toEntity()).collect(Collectors.toList());

        //entity 묶음을 db에 저장
        articleList.stream()
                .forEach(article -> articleRepository.save(article));

        // 강제 예외 발생
        articleRepository.findById(-1L).orElseThrow(
                ()->new IllegalArgumentException("결제 실패")
        );

        // 결과값 반환

        return articleList;
    }
}
