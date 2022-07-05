package com.hello.project.api;

import com.hello.project.domain.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthApiController {

    private final AuthService authService;

    @GetMapping("/api/auth")
    public boolean usernameCheck(String username) {
        return authService.usernameUniqueCheck(username);
    }



}
