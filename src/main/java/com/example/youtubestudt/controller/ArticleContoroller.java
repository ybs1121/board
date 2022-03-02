package com.example.youtubestudt.controller;

import com.example.youtubestudt.articlerepository.ArticleRepository;
import com.example.youtubestudt.dto.ArticleForm;
import com.example.youtubestudt.entity.Article;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@Slf4j //로깅
public class ArticleContoroller {

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm(){

        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm articleForm){
        //System.out.println(articleForm.toString());
        log.info(articleForm.toString());

        Article article = articleForm.toEntity();

       // System.out.println(article);
        log.info(article.toString());
        Article saved = articleRepository.save(article);
      //  System.out.println(saved);
        log.info(saved.toString());
        return "redirect:/articles/" + saved.getId();
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model){ //url에서 데이터 가져옴
        log.info("id = " + id);
       // Optional<Article> article = articleRepository.findById(id);
        Article article = articleRepository.findById(id).orElse(null);
        model.addAttribute("article",article);

        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model){
        List<Article> articleList = new ArrayList<>();
        articleList =  articleRepository.findAll();

        model.addAttribute("articleList",articleList);


        return "articles/index";
    }


    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        Article article = articleRepository.findById(id).orElse(null);
        model.addAttribute("article",article);
        return "articles/edit";
    }


    @PostMapping("/articles/update")
    public String update(ArticleForm articleForm) {

        log.info(articleForm.toString());

        Article article = articleForm.toEntity();

        Article target = articleRepository.findById(article.getId()).orElse(null);

        if(target != null){
            articleRepository.save(article);
        }
        else{
            log.info("없음");
        }

        return "redirect:/articles/" + article.getId();
    }

    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes){
        log.info("삭제요청");

        //삭제 대상 가져오기
        Article article = articleRepository.findById(id).orElse(null);

        // 대상 삭제
        if(article != null) {
            articleRepository.delete(article);
            redirectAttributes.addFlashAttribute("msg","삭제완료!");
        }
        else log.info("삭제대상이 없습니다.");

        // 결과 페이지로 리다이렉트 한다.
        return "redirect:/articles";


    }

}
