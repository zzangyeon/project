package com.hello.project;

import com.hello.project.domain.user.User;
import com.hello.project.domain.user.UserRepository;
import com.hello.project.domain.auth.SignupDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest

class ProjectApplicationTests {

	@Autowired
	UserRepository userRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void userEntity(){
		User user = new SignupDto("지연", "1234", "zxkim2004@naver.com", "최지연").toEntity();
		User userEntity = userRepository.save(user);
		System.out.println(userEntity);
	}

}
