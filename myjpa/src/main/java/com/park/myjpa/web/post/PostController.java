package com.park.myjpa.web.post;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;

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
import com.park.myjpa.web.post.dto.PostRespDto;
import com.park.myjpa.web.post.dto.PostSaveReqDto;
import com.park.myjpa.web.post.dto.PostUpdateReqDto;

import lombok.RequiredArgsConstructor;

/**
 * 
 * ORM = Object Relation Mapping 자바 오브젝트를 만들고
 *  DB에 테이블을 자동생성. 자바 오브젝트로 연관관꼐를 맺을 수 있음
 *
 */
@RequiredArgsConstructor
@RestController
public class PostController {
	private final PostRepository postRepository;
	private final HttpSession session;
	// 영속화를 위한 객체
	private final EntityManager em;

	@PostMapping("/post")
	public CommonRespDto<?> save(@RequestBody PostSaveReqDto postSaveReqDto) {

		// 원래는 세션값을 넣어야 함.
		// User user = new User(1L, "ssar", "1234", "email@nate.com",
		// LocalDateTime.now());

		User principal = (User) session.getAttribute("principal");

		if (principal == null) {
			return new CommonRespDto<>(-1, "실패", null);
		} else {
			// 실패 => Exception을 탄다.
			Post postEntity = postRepository.save(postSaveReqDto.toEntity());
			postEntity.setUser(principal);

			return new CommonRespDto<>(1, "성공", postEntity);

		}
	}

	// 리플렉션
	@GetMapping("/post/{id}")
	public CommonRespDto<?> findById(@PathVariable Long id) {
		// findById()호출 할 땐 Optional 설정을 해줘야 한다.(아니면 컴파일 오류가 뜬다.)
		// 왜냐하면 findById()에 리턴값이 Null일 경우에 처리하는 방법을 정해야하기 때문이다.
		// Optional 설정이란
		// 스프링이 사용자에게 리턴 값이 Null일 경우에 처리 방법에 대해 선택 사항을 주는 것이다.
		// Optional 에는 get(), orElseGet(), orElseThrow() 가 있다.
		// get() = 무조건 값을 꺼내는 조건 (위험하다)
		// orElseThrow() = Null이 발생하면 Exception을 리턴해준다.
		Post postEntity = postRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("id를 찾을 수 없습니다.");
		});
		
		PostRespDto postRespDto = new PostRespDto(postEntity);
		
		// MessageConverter가 모든 getter를 호출해서 json으로 만들어 준다.
		return new CommonRespDto<>(1, "성공", postEntity);
	}
	
	@GetMapping("/post")
	public CommonRespDto<?> findAll(){
		List<Post> postsEntity = postRepository.findAll();
		return new CommonRespDto<>(1, "성공", postsEntity);
	}
	
	@PutMapping("/post/{id}")
	public CommonRespDto<?> update(@PathVariable long id, 
			@RequestBody PostUpdateReqDto postUpdateReqDto){
		
		/*
		 * Post post = new Post(); em.persist(post);
		 */
		
		// 영속화 
		Post postEntity = postRepository.findById(id).orElseThrow(()-> {
			return new IllegalArgumentException("id를 찾을 수 없습니다.");
		});
		
		postEntity.setTitle(postUpdateReqDto.getTitle());
		postEntity.setContent(postUpdateReqDto.getContent());
		
		// 더티 체킹을 사용해야 하는데 그러려면 @Service만들어야 가능 
		Post postUpdateEntity = postRepository.save(postEntity);
		
		return new CommonRespDto<>(1, "성공", postUpdateEntity);
		
	}
	
	@DeleteMapping("/post/{id}")
	public CommonRespDto<?> update(@PathVariable long id){
		postRepository.deleteById(id);
		return new CommonRespDto<>(1, "성공", null);
		
	}
	
}
