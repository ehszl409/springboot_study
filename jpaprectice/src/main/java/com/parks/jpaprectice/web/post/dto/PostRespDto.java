package com.parks.jpaprectice.web.post.dto;

import java.time.LocalDateTime;

import com.parks.jpaprectice.domain.post.Post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class PostRespDto {
	private long id;
	private String title;
	private String content;
	private LocalDateTime createDate;
	private long userId;
	
	public PostRespDto(Post post) {
		super();
		this.id = post.getId();
		this.title = post.getTitle();
		this.content = post.getContent();
		this.createDate = post.getCreateDate();
		this.userId = post.getUser().getId();
	}
	
	
}
