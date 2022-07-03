package com.hello.project;

import com.hello.project.domain.article.ArticleDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/*
* 통합테스트 ( 모든 Bean들을 똑같이 IOC에 올리고 테스트 하는것)
* WebEnvironment.MOCK = 실제 톰캣이 아닌 모의환경에서 테스느 하는것
* WebEnvironment.RANDOM_PPORT = 실제 톰켓으로 테스트함
* @AutoConfigureMockMvc = MockMvc를 Ioc에 등록해줌.
* @Transactional = 각 테스트함수가 종료될 때마다 트랜잭션을 rollback해주는 어노테이션
*
* */

@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class ProjectApplicationTests {

//    @Autowired MockMvc mvc;
//
//
//    @Test
//    public void test() {
//        System.out.println("=====테스트===========");
//    }



}
