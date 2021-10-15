package com.hello.project.domain.article;

import com.hello.project.domain.comment.CommentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
//단위테스트 Controller, Filter, ControllerAdvice같은것 띄워줌...WebMvcTest
@WebMvcTest//AutoconfigureMockMvc가 안에 있음.
class ArticleControllerTest {

        @Autowired
        ArticleController articleController;

        @Autowired
        private MockMvc mvc;
        @MockBean//IOC 컨테이너에 등록됨.
        private ArticleService articleService;
        @MockBean
        private CommentService commentService;

        @Test
        public void saveTest() {
            System.out.println("=======테스트 시작=========");
        }
}