package com.hello.project.controller;

import com.hello.project.config.auth.PrincipalDetails;
import com.hello.project.domain.user.User;
import com.hello.project.dto.UserBlogDto;
import com.hello.project.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping("/blog/{id}")
    public String myBlog(@AuthenticationPrincipal PrincipalDetails principalDetails,@PathVariable Long id, Model model) {

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

    @GetMapping("/user/update")
    public String userUpdateForm(@AuthenticationPrincipal PrincipalDetails principalDetails,Model model) {
        User user = userService.getUser(principalDetails.getUser().getId());
        model.addAttribute("user", user);
        return "user/userUpdate";
    }

}