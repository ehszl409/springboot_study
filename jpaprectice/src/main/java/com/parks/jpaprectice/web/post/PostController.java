package com.parks.jpaprectice.web.post;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.parks.jpaprectice.domain.post.Post;
import com.parks.jpaprectice.domain.user.User;
import com.parks.jpaprectice.service.PostService;
import com.parks.jpaprectice.web.dto.CommonRespDto;
import com.parks.jpaprectice.web.post.dto.PostSaveReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class PostController {
	
	private final HttpSession session;
	private final PostService postService;

	
	// 인증만 필요
	@PostMapping("/post")
	public CommonRespDto<?> save(@RequestBody PostSaveReqDto postSaveReqDto){
		
		User principal = (User) session.getAttribute("principal");
		
		if(principal == null) {
			return new CommonRespDto<>(-1, "실패", null);
		} else {
			Post postEntity = postService.추가하기(postSaveReqDto, principal);
			return new CommonRespDto<>(1,"성공", postEntity);			
		}
	}
	
	// 인증만 필요
	@GetMapping("/post")
	public CommonRespDto<?> findAll(){
		return new CommonRespDto<>(1, "성공", postService.전체찾기());
	}
	
	// 인증만 필요
	@GetMapping("/post/{id}")
	public CommonRespDto<?> findById(@PathVariable long id){
		return new CommonRespDto<>(1, "성공", postService.한건찾기(id));
	}
	
	// 인증과 권한이 필요
	@PutMapping("/post/{id}")
	public CommonRespDto<?> Update(@PathVariable long id, @RequestBody PostSaveReqDto postSaveReqDto){
		return new CommonRespDto<>(1,"성공", postService.수정하기(id, postSaveReqDto));
	}
	
	// 인증과 권한이 필요 
	@DeleteMapping("/post/{id}")
	public CommonRespDto<?> DeleteById(@PathVariable long id){
		postService.삭제하기(id);
		return new CommonRespDto<>(1, "성공", null);
	}
}
