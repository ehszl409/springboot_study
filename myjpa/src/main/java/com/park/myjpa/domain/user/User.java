package com.park.myjpa.domain.user;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.park.myjpa.domain.post.Post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class User {
	@Id //PK
	@GeneratedValue(strategy = GenerationType.IDENTITY)//Table, auto_increment, sequence
	private long id;
	private String username;
	private String password;
	private String email;
	
	@CreationTimestamp // 자동으로 현재시간이 들어옴.
	private LocalDateTime createDate;
	
	// ORM이 실행될 때 FK의 주인이 아니라고 명시해서 컬럼 추가를 막는다.
	// 나는 FK의 주인이 아니다. FK는 user 변수명 이다.
	// 1:N 에서 N 테이블에선 LAZY를 걸어준다.
	// 역방향 매핑
	@JsonIgnoreProperties({"user"}) // post안에 있는 user의 getter를 호출하지 마라.
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<Post> post;
	
//	
//	@Transient
//	private int value;
}
