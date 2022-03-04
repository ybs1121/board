package com.example.youtubestudy.api;

import com.example.youtubestudy.service.ArticleService;
import com.example.youtubestudy.dto.ArticleForm;
import com.example.youtubestudy.entity.Article;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class ArticleApiController {

//    @Autowired
//    private ArticleRepository articleRepository;

    @Autowired // DI, 생성 객체를 가져와 연결!
    private ArticleService articleService;

    //GET
    @GetMapping("/api/articles")
    public List<Article> index(){
        return articleService.index();
    }

    @GetMapping("api/articles/{id}")
    public Article show(@PathVariable Long id){
        return articleService.show(id);

    }
//
    //POST

    @PostMapping("/api/articles")
    public ResponseEntity<Article> create(@RequestBody ArticleForm dto){
        Article created = articleService.create(dto);
        return (created != null)?
                ResponseEntity.status(HttpStatus.OK).body(created):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    //PATCH

    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id,
                          @RequestBody ArticleForm dto){

       Article updated = articleService.update(id,dto);
       return  (updated != null)?
                ResponseEntity.status(HttpStatus.OK).body(updated):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //DELETE

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id){
        Article deleted = articleService.delete(id);
        return (deleted != null) ? ResponseEntity.status(HttpStatus.OK).build():
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

    //트랜잭션 -> 실패시 롤백!
    @PostMapping("/api/transaction-test")
    public ResponseEntity<List<Article>> transactionTest(@RequestBody List<ArticleForm> dtos){
        List<Article> createdList = articleService.createArticle(dtos);
        return (createdList != null) ? ResponseEntity.status(HttpStatus.OK).body(createdList):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }


}
