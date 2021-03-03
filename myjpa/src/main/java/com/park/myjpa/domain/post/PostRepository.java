package com.park.myjpa.domain.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // 생략가능, 내부적으로 IoC에 등록됨.
public interface PostRepository extends JpaRepository<Post, Long>{

}
