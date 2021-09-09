package com.hello.project.domain.comment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hello.project.domain.article.Article;
import com.hello.project.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 100, nullable = false)
	private String content;
	
	@JsonIgnoreProperties({"articles"})
	@JoinColumn(name="user_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;
	
	@JoinColumn(name="article_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Article article;

}
