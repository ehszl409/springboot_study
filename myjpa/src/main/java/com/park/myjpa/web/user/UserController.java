package com.park.myjpa.web.user;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.park.myjpa.domain.post.Post;
import com.park.myjpa.domain.user.User;
import com.park.myjpa.domain.user.UserRepository;
import com.park.myjpa.web.dto.CommonRespDto;
import com.park.myjpa.web.post.dto.PostRespDto;
import com.park.myjpa.web.user.dto.UserJoinReqDto;
import com.park.myjpa.web.user.dto.UserLoginReqDto;
import com.park.myjpa.web.user.dto.UserRespDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class UserController {

	private final UserRepository userRepository;
	private final HttpSession session;

	// user 정보만 전달해준다.
	@GetMapping("/user")
	public CommonRespDto<?> findAll() {
		List<User> usersEntity = userRepository.findAll();

		List<UserRespDto> userRespDtos = new ArrayList<>();
		for (User user : usersEntity) {
			userRespDtos.add(new UserRespDto(user));
		}

//		List<UserRespDto> userRespDtos = usersEntity.stream().map((u)->{
//			return new UserRespDto(u);
//		}).collect(Collectors.toList());

		return new CommonRespDto<>(1, "성공", usersEntity);
	}

	// user와 
	@GetMapping("/user/{id}")
	public CommonRespDto<?> findById(@PathVariable Long id) {

		User userEntity = userRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("id를 찾을 수 없습니다.");
		});

		UserRespDto userRespDto = new UserRespDto(userEntity);

		// MessageConverter가 모든 getter를 호출해서 json으로 만들어 준다.
		return new CommonRespDto<>(1, "성공", userRespDto);
	}

	// user를 기준으로 post를 뿌려준다.
	@GetMapping("/user/{id}/post")
	public CommonRespDto<?> profile(@PathVariable Long id) {
		User userEntity = userRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("id를 찾을 수 없습니다.");
		});

		// MessageConverter가 모든 getter를 호출해서 json으로 만들어 준다.
		return new CommonRespDto<>(1, "성공", userEntity);
	}

	@PostMapping("/join") // 회원 가입
	public CommonRespDto<?> join(@RequestBody UserJoinReqDto userJoinReqDto) {
		User userEntity = userRepository.save(userJoinReqDto.toEntity());
		return new CommonRespDto<>(1, "성공", userEntity);

	}

	@PostMapping("login")
	public CommonRespDto<?> login(@RequestBody UserLoginReqDto userLoginReqDto) {
		// 로그인 하기 위한 JPA 레포 사용하는 법
		// 로그인은 Optional을 지정해주지 않아도 된다.
		User userEntity = userRepository.findByUsernameAndPassword(userLoginReqDto.getUsername(),
				userLoginReqDto.getPassword());

		if (userEntity == null) {
			return new CommonRespDto<>(-1, "실패", null);
		} else {
			userEntity.setPassword(null); // password를 응답해주는 것은 보안상 위험하다 .
			session.setAttribute("principal", userEntity); // 세션 생성
			return new CommonRespDto<>(1, "성공", userEntity);
		}

	}

}
