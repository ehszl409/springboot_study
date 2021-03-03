package com.park.myjpa.web.post.dto;

import com.park.myjpa.domain.post.Post;

import lombok.Data;
import lombok.Getter;

@Data
public class PostSaveReqDto {
	private String title;
	private String content;
	
	// 본 오브젝트로 바꿔주는 함수를 만들어 준다.
	public Post toEntity() {
		return Post.builder()
				.title(title)
				.content(content)
				.build();
	}
}
