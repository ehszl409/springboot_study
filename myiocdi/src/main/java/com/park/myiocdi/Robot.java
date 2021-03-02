package com.park.myiocdi;


import lombok.Getter;

/**
 * 
 * @author USER
 * 
 * IoC 컨테이너에 등록하기 = 스프링 서버가 실행될 때 등록된다.
 * IoC 컨테이너의 종류
 * Component(용도 없음 모든 어노테이션의 부모), configuration(설정 파일), Service(서비스), Repository(저장소), Bean(내부 메서드)
 *
 */

@Getter
public class Robot {
	private String name = "건담";
}	
