package com.hello.project.domain.test;

import com.hello.project.config.auth.PrincipalDetails;
import com.hello.project.domain.article.Article;
import com.hello.project.domain.article.ArticleRepository;
import com.hello.project.domain.test.Test;
import com.hello.project.domain.test.TestRepository;
import com.hello.project.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class TestController {

    private final TestRepository testRepository;
    private final ArticleRepository articleRepository;

    @GetMapping("/test")
    public String content(@AuthenticationPrincipal PrincipalDetails principalDetails) {

        return "1";
        }
}




