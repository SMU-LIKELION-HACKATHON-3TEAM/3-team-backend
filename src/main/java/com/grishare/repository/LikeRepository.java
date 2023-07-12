package com.grishare.repository;

import com.grishare.domain.LikePost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<LikePost,Long> {

    Optional<LikePost> findByPostIdAndUserId(Long postId, Long userId);
}
