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
        return "myblog";
    }

    @GetMapping("/user/update")
    public String userUpdateForm(@AuthenticationPrincipal PrincipalDetails principalDetails,Model model) {
        User user = userService.getUser(principalDetails.getUser().getId());
        model.addAttribute("user", user);
        return "user/userUpdate";
    }



}