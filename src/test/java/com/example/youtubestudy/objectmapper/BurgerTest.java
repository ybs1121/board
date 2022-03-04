package com.example.youtubestudy.objectmapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BurgerTest {

    @Test
    public void 자바_객체를_Json으로_변환() throws JsonProcessingException {
        //준비
        ObjectMapper objectMapper = new ObjectMapper();
        List<String> ingredient = Arrays.asList("통새우","패티");
        Burger burger = new Burger("슈비버거",5000,ingredient);
        //수행
        String json = objectMapper.writeValueAsString(burger);
        //예상

        String expect = "{\"name\":\"슈비버거\",\"price\":5000,\"ingredients\":[\"통새우\",\"패티\"]}";
        //검증

        assertEquals(expect,json);

        JsonNode jsonNode =objectMapper.readTree(json);
        System.out.println(jsonNode.toPrettyString());
    }

    @Test
    public void Json을_자바로_변환() throws JsonProcessingException {
        //준비
        ObjectMapper objectMapper = new ObjectMapper();
     //   String json = "{\"name\":\"슈비버거\",\"price\":5000,\"ingredients\":[\"통새우\",\"패티\"]}";
        //수행
        ObjectNode objectNode =objectMapper.createObjectNode();
        objectNode.put("name","슈비버거");
        objectNode.put("price",5000);

        ArrayNode arrayNode =objectMapper.createArrayNode();
        arrayNode.add("통새우");
        arrayNode.add("패티");

        objectNode.set("ingredients",arrayNode);

        String json =objectNode.toString();


        Burger burger =objectMapper.readValue(json,Burger.class);

        //예상

        List<String> ingredient = Arrays.asList("통새우","패티");
        Burger expected = new Burger("슈비버거",5000,ingredient);



        //검증

        assertEquals(expected.toString(),burger.toString());
        JsonNode jsonNode =objectMapper.readTree(json);

        System.out.println(jsonNode.toPrettyString());
        System.out.println(burger.toString());
    }

}