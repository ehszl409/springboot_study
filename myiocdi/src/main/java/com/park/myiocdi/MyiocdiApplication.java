package com.park.myiocdi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MyiocdiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyiocdiApplication.class, args);
	}
	
	// 다른 누군가가 함수를 호출하고 싶어서 IoC컨테이너에 등록해 놓는다.
	@Bean // 리턴 값을 IoC컨테이너에 저장한다.
	public Robot autobot() {
		return new Robot();
	}

}
