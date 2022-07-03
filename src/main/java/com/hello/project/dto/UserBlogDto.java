package com.hello.project.dto;

import com.hello.project.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserBlogDto {

    private User user;
    private boolean subscribeState;
    private boolean pageOwnerState;

}
