package com.grishare.repository;

import com.grishare.domain.LikeNation;
import com.grishare.domain.LikePost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeNationRepository extends JpaRepository<LikeNation,Long > {
    Optional<LikeNation> findByNationIdAndUserId(Long nationId, Long userId);
}
