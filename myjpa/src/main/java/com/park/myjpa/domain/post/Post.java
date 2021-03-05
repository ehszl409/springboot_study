package com.park.myjpa.domain.post;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.park.myjpa.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Post {
	@Id //PK
	@GeneratedValue(strategy = GenerationType.IDENTITY)//Table, auto_increment, sequence
	private long id;
	@Column(length = 60, nullable = false)
	private String title;
	@Lob // 대용량 데이터 
	private String content;
	
	// 연관관계 맻는 방법 FK의 주인인 곳에서 적어야 됨.
	// 전략은 2가지가 있다.
	// LAZY는 select를 두번 한다.
	// LAZY는 select를 한 번 해야지 영속성 컨텍스트에 등록되어 나중에 
	// getter를 통해 select가 가능하다.
	// EAGER은 join을 한다.
	// 순방향 매핑
	@ManyToOne(fetch = FetchType.LAZY) // 먼저 찾는다.
	@JoinColumn(name="userId")
	private User user;
	
	@CreationTimestamp // 자동으로 현재시간이 들어옴.
	private LocalDateTime createDate;
}
