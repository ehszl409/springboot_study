package com.park.myjpa.web;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.park.myjpa.domain.post.Post;
import com.park.myjpa.domain.post.PostRepository;
import com.park.myjpa.domain.user.User;
import com.park.myjpa.web.dto.CommonRespDto;
import com.park.myjpa.web.post.dto.PostSaveReqDto;
import com.park.myjpa.web.post.dto.PostUpdateReqDto;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor // DI
@RestController
public class TestController {
	

	
	private final PostRepository postRepository;

	@PostMapping("/test/post")
	public CommonRespDto<?> save(@RequestBody PostSaveReqDto postSaveReqDto) { // title, content
		
		User user = new User(1L, "ssar", "1234", "email@nate.com", LocalDateTime.now());
		
		Post postEntity = postRepository.save(postSaveReqDto.toEntity()); // 실패 => Exception을 탄다.
		postEntity.setUser(user);
		
		return new CommonRespDto<>(1, "성공",postEntity);
	}
	
	@GetMapping("/test/post")
	public CommonRespDto<?> findAll(){
		List<Post> postsEntity = postRepository.findAll();
		return new CommonRespDto<>(1, "성공", postsEntity);
	}
	
	@GetMapping("/test/post/{id}")
	public CommonRespDto<?> findById(@PathVariable Long id){
		
		Post postEntity = postRepository.findById(id).orElseThrow(()-> {
			return new IllegalArgumentException("id를 찾을 수 없습니다.");
		});
		
		return new CommonRespDto<>(1, "성공", postEntity);
	}
	
	@PutMapping("/test/post/{id}")
	public CommonRespDto<?> update(@PathVariable long id, @RequestBody PostUpdateReqDto postUpdateReqDto){
		Post postEntity = postRepository.findById(id).orElseThrow(()-> {
			return new IllegalArgumentException("id를 찾을 수 없습니다.");
		});
		
		postEntity.setTitle(postUpdateReqDto.getTitle());
		postEntity.setContent(postUpdateReqDto.getContent());
		
		// 더티 체킹을 사용해야 하는데 그러려면 @Service만들어야 가능 
		Post postUpdateEntity = postRepository.save(postEntity);
		
		return new CommonRespDto<>(1, "성공", postUpdateEntity);
		
	}
	
	@DeleteMapping("/test/post/{id}")
	public CommonRespDto<?> update(@PathVariable long id){
		postRepository.deleteById(id);
		return new CommonRespDto<>(1, "성공", null);
		
	}
	
	

}
