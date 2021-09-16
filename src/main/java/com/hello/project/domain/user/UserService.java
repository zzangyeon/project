package com.hello.project.domain.user;

import com.hello.project.domain.article.Article;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public User getUser(Long id) {
        User userEntity = userRepository.findById(id).get();
        return userEntity;
    }

    @Transactional
    public User userUpdate(Long id, User toEntity) {
        User userEntity = userRepository.findById(id).get();
        userEntity.setUsername(toEntity.getUsername());
        userEntity.setBlogName(toEntity.getBlogName());
        userEntity.setBlogIntro(toEntity.getBlogIntro());
        return userEntity;
    }
}
