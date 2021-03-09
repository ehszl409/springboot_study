package com.parks.jpaprectice.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	//1. namaingQuery findByLastnameAndFirstname
	User findByUsernameAndPassword(String username, String password);
	
	

}
