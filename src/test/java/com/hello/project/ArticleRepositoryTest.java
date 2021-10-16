package com.hello.project;

//단위 테스트 - DB고나련 Bean 이 IOC에 등록되면 됨

import com.hello.project.domain.article.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)//가짜 디비로 테스트,  Replace.NONE 실제 디비로 테스트(통합테스트에서 하면됨)
@DataJpaTest// Repository 들을 다 IOC에 등록해둠 (Extends(스프링)) 스프링환경이기때문
public class ArticleRepositoryTest {

//    @Autowired
//    private ArticleRepository articleRepository;


}
