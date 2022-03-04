package com.example.youtubestudt.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column
    private String title;

    @Column
    private String content;

    public void patch(Article article){
        if(article.title != null)
            this.title = title;
        if(article.content != null)
            this.content = content;
    }


}
