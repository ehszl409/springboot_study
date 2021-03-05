package com.park.myjpa.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
	
	// 로그인을 위해 함수를 만든다.
	
	// 1. namingQuery
	// select * from user where username = ?1;
	// User findByUsername(String username);
	
	// select * from user where username = ?1 and password = ?2;
	// ?1, ?2 는 인자의 순서
	User findByUsernameAndPassword(String usrname, String password);
	
	// 2. nativeQuery
	@Query(value = "select * from user where username = ?1 and password = ?2",
			nativeQuery = true)
	User mLogin(String username, String password);

}
