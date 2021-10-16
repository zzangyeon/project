package com.hello.project;

import com.hello.project.domain.article.ArticleRepository;
import com.hello.project.domain.article.ArticleService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/*
 단위테스트 -  Service 관련된 애들만 메모리에 띄우면 됨
 ArticleRepository - 가짜 객체로 만들 수 있음.
*/


@ExtendWith(MockitoExtension.class)
public class ArticleServiceTest {

//    @InjectMocks //ArticleService 객체가 만들어질때 ArticleServiceTest 파일에 @Mock로 등록된 모든 애들을 주입받는다.
//    private ArticleService articleService;
//
//    @Mock
//    private ArticleRepository articleRepository;



}
