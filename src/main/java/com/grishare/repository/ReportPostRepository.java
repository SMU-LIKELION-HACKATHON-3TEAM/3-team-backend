package com.grishare.repository;

import com.grishare.domain.ReportPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportPostRepository extends JpaRepository<ReportPost, Long> {
//    List<ReportPost> findByPostIdAndUserId(Long postId, Long userId);
    List<ReportPost> findByPostId(Long postId);
}
