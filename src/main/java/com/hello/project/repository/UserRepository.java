package com.hello.project.repository;

import com.hello.project.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByUsername(String username);

	/*User findByUsernameand();*/

}
