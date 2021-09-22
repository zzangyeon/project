package com.hello.project.domain.user;

import com.hello.project.config.auth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping("/myblog")
    public String  myblog(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {
        User user = userService.getUser(principalDetails.getUser().getId());
        model.addAttribute("user",user);
        model.addAttribute("isLogin", true);
        model.addAttribute("principal",principalDetails.getUser());
        return "user/myblog";
    }

    @GetMapping("/blog/{id}")
    public String  blog(@AuthenticationPrincipal PrincipalDetails principalDetails,@PathVariable Long id, Model model) {
        User user = userService.getUser(id);
        model.addAttribute("user",user);

        if(principalDetails == null){
            model.addAttribute("isLogin", false);
        }else{
            model.addAttribute("isLogin", true);
            model.addAttribute("principal",principalDetails.getUser());
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