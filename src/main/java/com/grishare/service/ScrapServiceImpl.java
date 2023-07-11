package com.grishare.service;

import com.grishare.domain.Post;
import com.grishare.domain.Scrap;
import com.grishare.domain.user.User;
import com.grishare.repository.PostRepository;
import com.grishare.repository.ScrapRepository;
import com.grishare.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScrapServiceImpl implements ScrapService{

    private final ScrapRepository scrapRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Override
    @Transactional
    public void addScrap(String userLoginId, Long postId){
        System.out.println("postId 3번째 확인! = " + postId);
        Post post = postRepository.findById(postId).orElse(null);
        User loginUSer = userRepository.findByUserLoginId(userLoginId).orElse(null);
        Scrap scrap = new Scrap(post,loginUSer);
    }

    @Override
    @Transactional
    public void deleteScrap(String userLoginId, Long postId){
        User loginUser = userRepository.findByUserLoginId(userLoginId).orElse(null);
        Scrap scrap = scrapRepository.findByPostIdAndUserId(postId,loginUser.getId())
                        .orElseThrow(null);

        scrapRepository.delete(scrap);
    }
    @Override
    public Boolean checkScrap(String userLoginId, Long postId){

        User loginUser = userRepository.findByUserLoginId(userLoginId).orElse(null);
        System.out.println("loginUserId 3트! = " + loginUser.getUserLoginId());
        return scrapRepository.findByPostIdAndUserId(postId,loginUser.getId())
                .isPresent();
    }
    @Override
    public void updateOfScrapPost(Long postId, User user){
        System.out.println("postId 2번째 확인!= " + postId);
        System.out.println("userloginId 2번째 확인! = " + user.getUserLoginId());

        Post post = postRepository.findById(postId)
                .orElseThrow(null);
        if(!checkScrap(user.getUserLoginId(),postId)) {
            post.increaseScrapCount();
            addScrap(user.getUserLoginId(), postId);
        }else if(checkScrap(user.getUserLoginId(),postId)){
            post.decreaseScrapCount();
            deleteScrap(user.getUserLoginId(), postId);
        }
    }
}
