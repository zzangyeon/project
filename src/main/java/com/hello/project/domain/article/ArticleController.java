package com.hello.project.domain.article;

import com.hello.project.config.auth.PrincipalDetails;
import com.hello.project.domain.article.Article;
import com.hello.project.domain.article.ArticleService;
import com.hello.project.domain.comment.Comment;
import com.hello.project.domain.comment.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ArticleController {

    private final ArticleService articleService;
    private final CommentService commentService;

    @GetMapping("/")
    public String homeArticleList(Model model,@AuthenticationPrincipal PrincipalDetails principalDetails,
                                  @PageableDefault(size=8,sort="id",direction = Sort.Direction.DESC) Pageable pageable) {
        if(principalDetails == null){
        model.addAttribute("isLogin",false);
        }else {
        model.addAttribute("isLogin",true);
        model.addAttribute("principal",principalDetails.getUser());
        }
        List<Article> articles = articleService.articleList(pageable);
        model.addAttribute("articles", articles);
        return "home";
    }

    @ResponseBody
    @GetMapping("/api")
    public List<Article> apiArticleList(Model model,@AuthenticationPrincipal PrincipalDetails principalDetails,
                                  @PageableDefault(size=8,sort="id",direction = Sort.Direction.DESC) Pageable pageable) {
        return articleService.articleList(pageable);
    }

    @GetMapping("/article/{id}")
    public String getArticle(@PathVariable Long id, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails,
                             @PageableDefault(size = 4,sort = "id",direction = Sort.Direction.DESC)Pageable pageable) {

        if (principalDetails == null) {
            model.addAttribute("isLogin", false);
        }else {
            model.addAttribute("isLogin", true);
            model.addAttribute("principal",principalDetails.getUser());
        }
        Article article = articleService.getArticle(id);
        List<Comment> comments = commentService.getComment(id, pageable);
        model.addAttribute("article", article);
        model.addAttribute("comments",comments);

        return "article";
    }

    @GetMapping("/write")
    public String writeArticle(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        return "write";
    }

    @PostMapping("/write")
    public String  saveArticle(ArticleDto articleDto,@AuthenticationPrincipal PrincipalDetails principalDetails) {

        if(articleDto.getThumbnail().isEmpty()) {
            //throw new CustomValidationException("이미지가 첨부되지 않았습니다.",null);
            System.out.println("=================썸네일이 첨부되지 않았습니다.=========");
        }

        Article articleEntity = articleService.saveArticle(articleDto, principalDetails.getUser().getId());
        Long id = articleEntity.getId();
        return "redirect:/article/"+id;
    }

    @GetMapping("/update")
    public String updateArticleForm(@ModelAttribute ArticleDto articleDto,Model model,@AuthenticationPrincipal PrincipalDetails principalDetails) {
        System.out.println("==============get update controller==============");
        Article article = articleService.getArticle(articleDto.getId());
        model.addAttribute("article", article);
        return "update";
    }

    @PostMapping("/update")
    public String updateArticle(ArticleDto articleDto,Model model,@AuthenticationPrincipal PrincipalDetails principalDetails) {
        System.out.println("==============post update controller==============");

        Article article = articleService.updateArticle(articleDto);
        model.addAttribute("article", article);
        Long id = article.getId();
        return "redirect:/article/"+id;
    }

    @GetMapping("/delete")
    public String deleteArticle(Long id) {
        System.out.println("====================delete controller=============");
        articleService.deleteArticle(id);
        return "redirect:/";
    }

}