package com.hello.project.domain.auth;

import com.hello.project.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@RequiredArgsConstructor//final 필드 DI할때 사용
@Controller//1.loC등록, 2.파일을 리턴하는 컨트롤러
public class AuthController {
	
	private static final Logger log = LoggerFactory.getLogger(AuthController.class);

	private final AuthService authService;

	//로그인
	@GetMapping("/auth/signin")
	public String signInForm() {
		return "auth/signin";
	}

	//회원가입 폼
	@GetMapping("/auth/signup")
	public String signUpForm() { return "auth/signup"; }

	//회원가입 하기
	@PostMapping("/auth/signup")//form으로 올때는 key=value 형태(x-www-form-urlencoded)방식으로 온다
	public String signUp(@Valid SignupDto signupDto, BindingResult bindingResult) {

			User user = signupDto.toEntity();
			log.info(user.toString());
			User userEntity = authService.회원가입(user);
			System.out.println(userEntity);
			return "auth/signin";
	}
	
}
