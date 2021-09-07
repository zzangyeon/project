package com.hello.project.service;

import com.hello.project.domain.User;
import com.hello.project.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service//1.ioC로 등록 , 2.트랜잭션 관리
@RequiredArgsConstructor
public class AuthService {
	
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Transactional
	public User 회원가입(User user) {

		String rawPassword = user.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
		user.setPassword(encPassword);
		user.setRole("ROLE_USER");
		User userEntity = userRepository.save(user);
		return userEntity;
	}
}
