package com.park.myiocdi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// IoC 컨테이너의 종류
// Component(용도 없음 모든 어노테이션의 부모), configuration(설정 파일), Service(서비스), Repository(저장소), Bean

@RestController
public class PostController {
	
	/*
	 * // DI의존성 주입
	 * 
	 * @Autowired private Robot robot;
	 */

	private final Robot robot;
	
	public PostController(Robot robot) {
		this.robot = robot;
	}
	
	@GetMapping("/")
	public String home() {
		return "home" + robot.getName();
	}
	
	
	
}
