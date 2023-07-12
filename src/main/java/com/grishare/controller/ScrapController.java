package com.grishare.controller;

import com.grishare.domain.user.CustomUserDetail;
import com.grishare.domain.user.User;
import com.grishare.repository.UserRepository;
import com.grishare.service.PostServiceImpl;
import com.grishare.service.ScrapServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ScrapController {
    private final ScrapServiceImpl scrapService;
    private final UserRepository userRepository;
    private PostServiceImpl postService;
    // 스크랩 기능
    @PostMapping("/posts/{postId}/scrap")
    public ResponseEntity<HttpStatus> Scrap(@PathVariable Long postId, @AuthenticationPrincipal CustomUserDetail customUserDetail){
        try {
            User user = customUserDetail.getUser();
            scrapService.updateOfScrapPost(postId,user);
            ResponseEntity
                    .status(HttpStatus.ACCEPTED);
            } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}


