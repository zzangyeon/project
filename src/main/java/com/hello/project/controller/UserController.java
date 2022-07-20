package com.hello.project.controller;

import com.hello.project.config.auth.PrincipalDetails;
import com.hello.project.domain.user.User;
import com.hello.project.dto.UserBlogDto;
import com.hello.project.domain.user.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @ApiOperation(value = "내 블로그", notes = "내 블로그 접속")
    @GetMapping("/blog/{id}")
    public String myBlog(@ApiIgnore @AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable Long id, Model model) {

        if(principalDetails == null){
            UserBlogDto blogDto = userService.getBlogUser(id);
            model.addAttribute("isLogin", false);
            model.addAttribute("blogDto",blogDto);
        }else{
            UserBlogDto blogDto = userService.getBlogDto(id, principalDetails.getUser().getId());
            model.addAttribute("isLogin", true);
            model.addAttribute("principal",principalDetails.getUser());
            model.addAttribute("blogDto", blogDto);
        }
        return "user/myblog";
    }

    @ApiOperation(value = "블로그 수정 폼", notes = "블로그 수정 폼 가져오기")
    @GetMapping("/user/update")
    public String userUpdateForm(@ApiIgnore @AuthenticationPrincipal PrincipalDetails principalDetails,Model model) {
        User user = userService.getUser(principalDetails.getUser().getId());
        model.addAttribute("user", user);
        return "user/userUpdate";
    }

}