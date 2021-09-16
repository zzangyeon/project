package com.hello.project.domain.test;

import com.hello.project.domain.article.Article;
import com.hello.project.domain.article.ArticleRepository;
import com.hello.project.domain.test.Test;
import com.hello.project.domain.test.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class TestController {

    private final TestRepository testRepository;
    private final ArticleRepository articleRepository;

    @ResponseBody
    @GetMapping("/test")
    public String content() {
        Test test = testRepository.findById(2L).get();
        String content = test.getContent();
        return "test";
    }

}
