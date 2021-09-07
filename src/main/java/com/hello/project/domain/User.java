package com.hello.project.domain;

//JPA - Java Persistence API(자바로 데이터를 영구적으로 저장(DB)할 수 있는 API를 제공)
//ORM - object relative mapping - 자바에서 클래스를 만들면 관계형 dB에 테이블이 만들어져 매핑이 된다.

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
	
	@Id//primary key가 필요해서 이용하는 어노
	@GeneratedValue(strategy = GenerationType.IDENTITY)//번호 증가 전략이 데이터베이스를 따라간다.(오라클이면 오라클 마리아면 마리아)
	private int id;

	@Column(length = 100, unique = true)
	private String username;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String name;
	private String website;
	private String bio;//자기소개

	@Column(nullable = false)
	private String email;
	private String phone;
	private String gender;
	
	private String profileImageUrl;//유저사진
	private String role;//권한
	
/*	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"user"})//JSON으로 파싱시 Image에 "user"는 파싱을 하지 않는다(getter 호출x)
	private List<Image> images;*/
	
	private LocalDateTime createDate;
	
	@PrePersist//DB에 값이 insert되기 직전에 실행되는 어노테이션
	public void createDate() {
		this.createDate=LocalDateTime.now();
	}
	

}
