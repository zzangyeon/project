package com.hello.project.config.auth;

import com.hello.project.domain.user.User;
import com.hello.project.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipaldetailsService implements UserDetailsService {
//security config에 적어놓은 /auth/signin이 post방식으로 요청 오면! UserdetailsService를 구현한 PrincipaldetailsService의 loadUserByUsername이 실행됨.

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User userEntity = userRepository.findByUsername(username);
        if (userEntity == null) {
            return null;
        } else {
            return new PrincipalDetails(userEntity);
        }
        //리턴이 잘 된면 알아서 UserDetails 타입을 세션으로 만들어줌
    }
}
