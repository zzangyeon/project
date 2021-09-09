package com.hello.project.domain.user;

//JPA - Java Persistence API(자바로 데이터를 영구적으로 저장(DB)할 수 있는 API를 제공)
//ORM - object relative mapping - 자바에서 클래스를 만들면 관계형 dB에 테이블이 만들어져 매핑이 된다.

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hello.project.domain.article.Article;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 100, unique = true)
	private String username;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String email;

	private String bio;//자기소개
	private String profileImageUrl;//유저사진
	private String role;//권한
	
	@JsonIgnoreProperties({"user"})
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<Article> articles;

}
