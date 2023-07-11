package com.grishare.repository;

import com.grishare.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    public List<Post> findAllByNationId(long nationId);
      //이은지 코드 (마이페이지에서) 작성한 글 조회
    List<Post> findAllByUserId(String userId);
}