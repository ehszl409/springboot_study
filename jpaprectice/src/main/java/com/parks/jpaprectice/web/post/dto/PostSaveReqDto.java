package com.parks.jpaprectice.web.post.dto;

import com.parks.jpaprectice.domain.post.Post;
import com.parks.jpaprectice.domain.user.User;

import lombok.Data;

@Data
public class PostSaveReqDto {

	private String title;
	private String content;
	
	public Post toEntity() {
		return new Post().builder()
				.title(title)
				.content(content)
				.build();
	}
	
}
