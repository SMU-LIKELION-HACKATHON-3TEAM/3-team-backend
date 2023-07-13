package com.grishare.service;

import com.grishare.domain.user.User;

public interface LikeNationService {
    public void addLike(String userLoginId, Long postId);

    public Boolean checkLike(String userLoginId, Long postId);

    public void deleteLike(String userLoginId, Long postId);
    public void updateOfLikeNation(final Long postId, final User user);
}
