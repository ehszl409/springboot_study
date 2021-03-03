package com.park.myjpa.web.post;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * ORM = Object Relation Mapping 
 * 자바 오브젝트를 만들고 DB에 테이블을 자동생성. 자바 오브젝트로 연관관꼐를 맺을 수 있음
 *
 */
@RestController
public class PostController {

	@GetMapping("/")
	public String home() {
		return "test";
	}
}
