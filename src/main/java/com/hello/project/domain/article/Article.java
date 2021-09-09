package com.hello.project.domain.article;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hello.project.domain.time.TimeEntity;
import com.hello.project.domain.comment.Comment;
import com.hello.project.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Article extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    private String discription;
    private String content;
    private String thumbnailUrl;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnoreProperties({"article"})
    @OneToMany(mappedBy = "article")
    private List<Comment> comments;


}
