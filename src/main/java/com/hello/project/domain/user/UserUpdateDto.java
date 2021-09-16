package com.hello.project.domain.user;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDto {

    @NotBlank
    private String username;
    @NotBlank
    private String blogName;
    private String blogIntro;
    private String profileImageUrl;


    public User toEntity() {
        return User.builder()
                .username(username)
                .blogName(blogName)
                .blogIntro(blogIntro)
                .profileImageUrl(profileImageUrl)
                .build();
    }

}
