package com.parks.jpaprectice.service;



import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.parks.jpaprectice.domain.post.Post;
import com.parks.jpaprectice.domain.post.PostRepository;
import com.parks.jpaprectice.domain.user.User;
import com.parks.jpaprectice.web.post.dto.PostRespDto;
import com.parks.jpaprectice.web.post.dto.PostSaveReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PostService {

	private final PostRepository postRepo;
	
	@Transactional
	public Post 추가하기(PostSaveReqDto postSaveReqDto, User principal) {
		Post post = postSaveReqDto.toEntity();
		post.setUser(principal);
		Post postEntity = postRepo.save(post);
		
		return postEntity;
	}
	
	@Transactional(readOnly = true)
	public List<PostRespDto> 전체찾기(){
		List<Post> posts = postRepo.findAll();
		List<PostRespDto> postRespDtos = new ArrayList<>();
		for (Post post : posts) {
			postRespDtos.add(new PostRespDto(post));
		}
		return postRespDtos;
	}
	
	@Transactional(readOnly = true)
	public PostRespDto 한건찾기(long id) {
		Post postEntity = postRepo.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("id를 찾을 수 없습니다.");
		});
		
		PostRespDto postRespDto = new PostRespDto(postEntity);
		
		return postRespDto;
	}
	
	@Transactional
	public Post 수정하기(long id, PostSaveReqDto postSaveReqDto) {
		Post postEntity = postRepo.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("id를 찾을 수 없습니다.");
		});
		
		postEntity.setTitle(postSaveReqDto.getTitle());
		postEntity.setContent(postSaveReqDto.getContent());
		
		// 서비스 종료시 영속성 컨텍스트에 영속화 되어있는 모든 객체의 변경을 감지하여 
		// 변경된 아이들을 flush 한다. (commit) = 더티체킹
		// 그래서 save를 해주지 않아도 자동으로 수정이 된다.
		
		return postEntity;
	}
	
	// Delete도 I/O가 발생해서 트랜잭션이 발생한다.
	@Transactional
	public void 삭제하기(long id) {
		postRepo.deleteById(id);
	}
	
}
