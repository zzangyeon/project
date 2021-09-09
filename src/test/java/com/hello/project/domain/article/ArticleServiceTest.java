package com.hello.project.domain.article;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleServiceTest {

    @Autowired ArticleRepository articleRepository;
    @Autowired ArticleService articleService;

    @Test
    void articleList() {

//        articleRepository.save(new Article(null,"title1",null,"content1",null,null));
//        articleRepository.save(new Article(null,"title2",null,"content2",null,null));
//        articleRepository.save(new Article(null,"title3",null,"content3",null,null));
//        articleRepository.save(new Article(null,"title4",null,"content4",null,null));
//        articleRepository.save(new Article(null,"title5",null,"content5",null,null));

        /*Slice<Article> articles = articleService.articleList();
        for (Article article : articles) {
            System.out.println(article.getContent());
        }*/

    }
}