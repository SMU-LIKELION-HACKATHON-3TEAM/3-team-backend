package com.grishare.service;

import com.grishare.domain.Like;
import com.grishare.domain.Post;
import com.grishare.domain.Scrap;
import com.grishare.domain.user.User;
import com.grishare.repository.LikeRepository;
import com.grishare.repository.PostRepository;
import com.grishare.repository.ScrapRepository;
import com.grishare.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Override
    public void addLike(String userLoginId, Long postId){
        Post post = postRepository.findById(postId).orElse(null);
        User loginUSer = userRepository.findByUserLoginId(userLoginId).orElse(null);
        Like like = new Like(post,loginUSer);
    }

    @Override
    public void deleteLike(String userLoginId, Long postId){
        Post post = postRepository.findById(postId).orElse(null);
        User loginUser = userRepository.findByUserLoginId(userLoginId).orElse(null);
        Like like = likeRepository.findByPostAndUser(post,loginUser)
                .orElseThrow(null);

        likeRepository.delete(like);
    }
    @Override
    public boolean checkLike(String userLoginId, Long postId){
        Post post = postRepository.findById(postId).orElse(null);
        User loginUser = userRepository.findByUserLoginId(userLoginId).orElse(null);
        return likeRepository.findByPostAndUser(post,loginUser)
                .isPresent();
    }
    @Override
    public void updateOfLikePost(Long postId, User user){
        Post post = postRepository.findById(postId)
                .orElseThrow(null);
        if(!checkLike(user.getUserLoginId(),postId)) {
            addLike(user.getUserLoginId(), postId);

        }else {
            deleteLike(user.getUserLoginId(), postId);
        }
    }
}
