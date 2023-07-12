package com.grishare.repository;

import com.grishare.domain.Like;
import com.grishare.domain.Post;
import com.grishare.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like,Long> {
    Optional<Like> findByPostAndUser(Post post, User user);
    List<Like> findAllByPostAndUser(Post post, User user);
}
