package com.example.youtubestudt.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstApiContorioller {
    @GetMapping("/api/hello")
    public String hello(){
        return "hello";
    }
}
