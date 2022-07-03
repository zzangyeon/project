package com.hello.project.controller;

import com.hello.project.config.auth.PrincipalDetails;
import com.hello.project.domain.article.ArticleRepository;
import com.hello.project.domain.test.TestRepository;
import com.hello.project.dto.UserDto;
import com.hello.project.handler.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
public class TestController {

    private final TestRepository testRepository;
    private final ArticleRepository articleRepository;

    @GetMapping("/test")
    public String test(@Valid UserDto userDto, BindingResult bindingResult) {
        return "/home";
    }



}




