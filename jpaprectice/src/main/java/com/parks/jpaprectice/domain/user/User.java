package com.parks.jpaprectice.domain.user;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.parks.jpaprectice.domain.post.Post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 10, nullable = false)
	private String username;
	@Column(length = 10, nullable = false)
	private String password;
	private String email;
	@CreationTimestamp
	private LocalDateTime createData;

	// ORM이 실행될 때 FK의 주인이 아니라고 명시해서 컬럼 추가를 막는다.
	// 나는 FK의 주인이 아니다. FK는 user 변수명 이다.
	// 1:N 에서 N 테이블에선 LAZY를 걸어준다.
	// 역방향 매핑
	// Post속 user 변수를 검색하지 말아라
	@JsonIgnoreProperties({"user"}) // post안에 있는 user의 getter를 호출하지 마라.
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<Post> posts;
}
