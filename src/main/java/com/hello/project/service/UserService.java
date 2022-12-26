package com.hello.project.service;

import com.hello.project.domain.user.User;
import com.hello.project.repository.SubscribeRepository;
import com.hello.project.domain.user.UserBlogDto;
import com.hello.project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final SubscribeRepository subscribeRepository;

    @Transactional(readOnly = true)
    public User getUser(Long id) {
        User userEntity = userRepository.findById(id).get();
        return userEntity;
    }

    @Transactional(readOnly = true)
    public UserBlogDto getBlogUser(Long id) {
        UserBlogDto userBlogDto = new UserBlogDto();
        User userEntity = userRepository.findById(id).get();
        userBlogDto.setUser(userEntity);
        return userBlogDto;
    }

    @Transactional
    public User userUpdate(Long id, User toEntity) {
        User userEntity = userRepository.findById(id).get();
        userEntity.setUsername(toEntity.getUsername());
        userEntity.setBlogName(toEntity.getBlogName());
        userEntity.setBlogIntro(toEntity.getBlogIntro());
        return userEntity;
    }

    @Transactional(readOnly = true)
    public UserBlogDto getBlogDto(Long blogUserId , Long principalId) {

        User userEntity = userRepository.findById(blogUserId).orElseThrow(()->{
            throw new RuntimeException("해당 프로필 페이지는 없는 페이지입니다.");
        });

        UserBlogDto userBlogDto = new UserBlogDto();
        userBlogDto.setUser(userEntity);
        userBlogDto.setPageOwnerState(blogUserId == principalId);

        int count = subscribeRepository.subscribeState(principalId, blogUserId);
        userBlogDto.setSubscribeState(count == 1);

        return userBlogDto;
    }
}
