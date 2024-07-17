package com.sandro.list.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/articles")
@RestController
public class ArticleController {

    @PostMapping
    public String saveArticle() {
        return "성공";
    }

}
