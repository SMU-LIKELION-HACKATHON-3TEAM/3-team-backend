package com.grishare.repository;

import com.grishare.domain.LikePost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<LikePost,Long> {

    Optional<LikePost> findByPostIdAndUserId(Long postId, Long userId);
}
