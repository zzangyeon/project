package com.hello.project.api;

import com.hello.project.config.auth.PrincipalDetails;
import com.hello.project.domain.article.Article;
import com.hello.project.service.ArticleService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ArticleApiController {

    private final ArticleService articleService;

    @ApiOperation(value = "글 목록", notes = "글 목록 가져오기")
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok"),
            @ApiResponse(code = 404, message = "page not found")
    })
    @ResponseBody
    @GetMapping("/api")
    public List<Article> apiArticleList(Model model, @ApiIgnore @AuthenticationPrincipal PrincipalDetails principalDetails,
                                        @ApiIgnore @PageableDefault(size=8,sort="id",direction = Sort.Direction.DESC) Pageable pageable) {
        return articleService.articleList(pageable);
    }
}
