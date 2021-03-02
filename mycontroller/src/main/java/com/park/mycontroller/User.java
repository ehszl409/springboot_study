package com.park.mycontroller;

public class User {

	// 1. 필드 확인
	private String username;
	private String password;
	
	// 2. setter 확인
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		System.out.println("username is park");
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		System.out.println("password is park");
		this.password = password;
	}
	
	
	
}
