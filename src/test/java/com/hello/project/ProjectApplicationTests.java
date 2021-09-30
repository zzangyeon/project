package com.hello.project;

import com.hello.project.domain.article.ArticleDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProjectApplicationTests {

    @Test
    public void test() {

        ArticleDto articleDto = new ArticleDto();
        String title = articleDto.getTitle();
        Assertions.assertThat(title).isEqualTo("hello");

    }

}
