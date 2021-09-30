package com.hello.project.domain.article;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hello.project.domain.comment.Comment;
import com.hello.project.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.nio.file.Files;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDto {

    private Long id;
    private String title;
    private String discription;
    private String content;
    private MultipartFile thumbnail;
//    private User user;
//    private List<Comment> comments;


    public Article toEntity(User user,String thumbnailUrl){
        return Article.builder()
                .title(title)
                .content(content)
                .discription(discription)
                .thumbnailUrl(thumbnailUrl)
                .user(user)
                .build();
    }


}
