package com.hello.project.domain.article;

import com.hello.project.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleUpdteDto {

    private Long id;
    @NotBlank
    private String title;
    private String description;
    @NotBlank
    private String content;

    private MultipartFile thumbnail;
//    private User user;
//    private List<Comment> comments;

    public Article toEntity(User user,String thumbnailUrl){
        return Article.builder()
                .title(title)
                .content(content)
                .description(description)
                .thumbnailUrl(thumbnailUrl)
                .user(user)
                .build();
    }


}
