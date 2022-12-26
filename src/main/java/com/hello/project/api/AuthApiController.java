package com.hello.project.api;

import com.hello.project.service.AuthService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthApiController {

    private final AuthService authService;

    @ApiOperation(value = "유저 아이디 검사", notes = "유저 아이디 중복 검사")
    @ApiResponses({
            @ApiResponse(code = 200, message = "ok~!"),
            @ApiResponse(code = 404, message = "page not found~!")
    })
    @ApiImplicitParam(name = "username", value = "유저 아이디", example = "hello")
    @GetMapping("/api/auth")
    public boolean usernameCheck(String username) {
        return authService.usernameUniqueCheck(username);
    }



}
