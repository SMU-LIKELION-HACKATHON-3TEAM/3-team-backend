package com.grishare.repository;

import com.grishare.domain.Post;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @EntityGraph(attributePaths = {"user", "postImage"})
    List<Post> findAll();
    public List<Post> findAllByNationId(long nationId);

    List<Post> findAllByUserId(Long userId);
}

