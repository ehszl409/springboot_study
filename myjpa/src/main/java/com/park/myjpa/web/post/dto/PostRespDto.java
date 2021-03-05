package com.park.myjpa.web.post.dto;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.park.myjpa.domain.post.Post;
import com.park.myjpa.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
// User 데이터는 제외하고 Post만 리턴해주는 Dto
public class PostRespDto {
	private long id;
	private String title;
	private String content;
	private LocalDateTime createDate;
	
	public PostRespDto(Post post) {
		this.id = post.getId();
		this.title = post.getTitle();
		this.content = post.getContent();
		this.createDate = post.getCreateDate();
	}
}
