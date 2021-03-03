package com.park.myjpa.domain.user;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

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
	private String passrword;
	private String email;
	
	@CreationTimestamp // 자동으로 현재시간이 들어옴.
	private LocalDateTime createDate;
}
