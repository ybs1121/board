package com.example.youtubestudy.ioc;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

@Component// 해당 클래스를 객체로 만들고, 이를 Ioc컨테이너에 등록
@Slf4j
public class IngredientFactory {


    public Ingredient get(String menu) {
        switch (menu){
            case "돈가스":
                log.info("돈가스 반환");
                return new Pork("한돈 등심");
            case "스테이크":
                return new Beef("한우 꽃등심");
            case "크리스피 치킨":
                return new Chicken("국내산 10호 닭");
            default:
                log.info("null 반환");
                return null;
        }
    }
}
