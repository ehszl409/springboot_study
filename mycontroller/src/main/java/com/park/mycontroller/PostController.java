package com.park.mycontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {
	
	 
	@GetMapping("/")
	public String home() {
		return "데이터";
	}
	
	@GetMapping("/test/post/data")
	public String PostData(String username, String pass) {
		return "username : " + username + "password : " + pass;
	}
	
	@PostMapping("/test/post")
	public String PostData2(String username, String pass) {
		return "username : " + username + " password : " + pass;
	}
	
	
	@PostMapping("/test/post/Object")
	public User PostDataObject(User user) {
		return user;
	}
	
	// json 데이터를 던지고 받는다.
	// 요청을 키 벨류이고 받는 인자가 오브젝트이면
	// 해당 오브젝트 필드와 일치하는 키 값이 있는지 스프링 부트가 자동으로 확인해준다.
	// 그리고 필드와 키값이 일치하면 해당 필드의 setter 함수를 찾아낸뒤 setter함수를 호출한다.
	// 나머지는 setter함수와 postMapping이 되어 있는 함수 로직에 따라 진행된다.
	@PostMapping("/test/post/json")
	public User PostDataJson(@RequestBody User user) {
		return user;
	}
	
	@PostMapping("/test/post/{id}")
	public String PostDatapath(@PathVariable int id) {
		return "id : " + id;
	}
	
	
}

