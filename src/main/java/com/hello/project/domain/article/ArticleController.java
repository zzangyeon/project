package com.hello.project.domain.article;

import com.hello.project.domain.article.Article;
import com.hello.project.domain.article.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/")
    public String homeArticleList(Model model){
        List<Article> articles = articleService.articleList();
        model.addAttribute("articles", articles);
        System.out.println();
        return "home";
    }

    @GetMapping("/article")
    public String getArticle(@Param("articleId") Long articleId,Model model){
        Article article = articleService.getArticle(articleId);
        model.addAttribute("article", article);
        return "article";
    }
}
