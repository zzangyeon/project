package com.hello.project.domain.auth;

import com.hello.project.domain.user.User;
import com.hello.project.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service//1.ioC로 등록 , 2.트랜잭션 관리
@RequiredArgsConstructor
public class AuthService {
	
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	@Transactional(readOnly = true)
	public boolean usernameUniqueCheck(String username) {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			return true;
		}else {
			return false;
		}
	}

	@Transactional
	public User 회원가입(User user) {
		String encPassword = bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(encPassword);
		user.setRole("ROLE_USER");
		User userEntity = userRepository.save(user);
		return userEntity;
	}
}
