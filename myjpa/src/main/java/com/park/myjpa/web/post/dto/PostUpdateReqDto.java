package com.park.myjpa.web.post.dto;

import com.park.myjpa.domain.post.Post;

import lombok.Data;
import lombok.Getter;

@Data
public class PostUpdateReqDto {
	// 수정시에는 NotNull이 없어도 된다.
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
