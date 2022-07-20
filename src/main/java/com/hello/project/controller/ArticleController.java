package com.hello.project.controller;

import com.hello.project.config.auth.PrincipalDetails;
import com.hello.project.domain.article.Article;
import com.hello.project.domain.article.ArticleDto;
import com.hello.project.domain.article.ArticleService;
import com.hello.project.domain.article.ArticleUpdteDto;
import com.hello.project.domain.comment.Comment;
import com.hello.project.domain.comment.CommentService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.ServletRequest;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ArticleController {
    private final ArticleService articleService;
    private final CommentService commentService;

    @ApiOperation(value = "홈 글 목록", notes = "메인페이지")
    @GetMapping("/")
    public String homeArticleList(Model model, @ApiIgnore @AuthenticationPrincipal PrincipalDetails principalDetails,
                                  @ApiIgnore @PageableDefault(size=8,sort="id",direction = Sort.Direction.DESC) Pageable pageable,
                                  ServletRequest request) {

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

    @ApiOperation(value = "글 목록", notes = "글 목록 가져오기")
    @GetMapping("/article/{id}")
    public String getArticle(@PathVariable Long id, Model model, @ApiIgnore @AuthenticationPrincipal PrincipalDetails principalDetails,
                             @ApiIgnore @PageableDefault(size = 4,sort = "id",direction = Sort.Direction.DESC)Pageable pageable) {

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

    @ApiOperation(value = "글쓰기 폼", notes = "글쓰기 폼 가져오기")
    @GetMapping("/write")
    public String articleWriteForm( @ApiIgnore @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return "write";
    }

    @ApiOperation(value = "글 저장", notes = "글 저장하기")
    @PostMapping("/write")
    public String saveArticle(@Valid ArticleDto articleDto, BindingResult bindingResult,
                              @ApiIgnore @AuthenticationPrincipal PrincipalDetails principalDetails) {
        Article articleEntity = articleService.saveArticle(articleDto, principalDetails.getUser().getId());
        Long id = articleEntity.getId();
        return "redirect:/article/"+id;
    }

    @ApiOperation(value = "글 수정 폼", notes = "글 수정 폼 가져오기")
    @GetMapping("/update")
    public String articleUpdateForm(@Valid ArticleDto articleDto, BindingResult bindingResult, Model model,
                                    @ApiIgnore @AuthenticationPrincipal PrincipalDetails principalDetails) {
        Article article = articleService.getArticle(articleDto.getId());
        model.addAttribute("article", article);
        return "update";
    }

    @ApiOperation(value = "글 수정", notes = "글 수정하기")
    @PostMapping("/update")
    public String updateArticle(@Valid ArticleUpdteDto articleUpdateDto, BindingResult bindingResult, Model model,
                                @ApiIgnore @AuthenticationPrincipal PrincipalDetails principalDetails) {
        System.out.println("==="+ articleUpdateDto);
        Article article = articleService.updateArticle(articleUpdateDto);
        model.addAttribute("article", article);
        Long id = article.getId();
        return "redirect:/article/"+id;
    }

    @ApiOperation(value = "글 삭제", notes = "글 삭제하기")
    @GetMapping("/delete")
    public String deleteArticle(Long id) {
        articleService.deleteArticle(id);
        return "redirect:/";
    }


}