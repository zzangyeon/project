package com.hello.project.domain.article;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hello.project.domain.comment.Comment;
import com.hello.project.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
public class ArticleDto {

    private Long id;
    private String title;
    private String discription;
    private String content;
    private User user;
    private List<Comment> comments;


}
