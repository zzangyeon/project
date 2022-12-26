package com.hello.project.controller;

import com.hello.project.config.jwt.JwtFilter;
import com.hello.project.config.jwt.TokenProvider;
import com.hello.project.service.AuthService;
import com.hello.project.dto.LoginDto;
import com.hello.project.dto.SignupDto;
import com.hello.project.domain.user.User;
import com.hello.project.dto.TokenDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;
	private final TokenProvider tokenProvider;
	private final AuthenticationManagerBuilder authenticationManagerBuilder;

	@ApiOperation(value = "로그인 폼", notes = "로그인 폼 가져오기")
	@GetMapping("/auth/signin")
	public String signInForm() {
		return "auth/signin";
	}

	@ApiOperation(value = "회원가입 폼", notes = "회원가입 폼 가져오기")
	@GetMapping("/auth/signup")
	public String signUpForm(){
		return "auth/signup";
	}

	@ApiOperation(value = "회원가입", notes = "회원가입하기")
	@PostMapping("/auth/signup")//form으로 올때는 key=value 형태(x-www-form-urlencoded)방식으로 온다
	public String signUp(@Valid SignupDto signupDto, BindingResult bindingResult) {

			User user = signupDto.toEntity();
			User userEntity = authService.회원가입(user);
			System.out.println(userEntity);
			return "auth/signin";
	}

	@ApiOperation(value = "로그인", notes = "로그인하기")
	@ResponseBody
	@PostMapping("/login2")
	public ResponseEntity<TokenDto> login2(@Valid LoginDto loginDto) {

		UsernamePasswordAuthenticationToken authenticationToken =
				new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

		Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String username = loginDto.getUsername();

		String jwt = tokenProvider.createToken(authentication);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
		return new ResponseEntity<>(new TokenDto(jwt), httpHeaders, HttpStatus.OK);
	}
	
}
