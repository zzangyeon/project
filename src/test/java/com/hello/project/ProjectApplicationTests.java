package com.hello.project;

import com.hello.project.domain.subscribe.Subscribe;
import com.hello.project.domain.subscribe.SubscribeRepository;
import com.hello.project.domain.subscribe.SubscribeRespDto;
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
	@Autowired
	SubscribeRepository subscribeRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void userEntity(){
		User user = new SignupDto("지연", "1234", "zxkim2004@naver.com", "최지연").toEntity();
		User userEntity = userRepository.save(user);
		System.out.println(userEntity);
	}

	@Test
	void countSubscribeState(){

		int state = subscribeRepository.subscribeState(6L,1L);
		System.out.println("state = " + state);
	}


}
