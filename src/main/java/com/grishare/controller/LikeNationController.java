package com.grishare.controller;

import com.grishare.domain.user.CustomUserDetail;
import com.grishare.domain.user.User;
import com.grishare.repository.UserRepository;
import com.grishare.service.LikeNationServiceImpl;
import com.grishare.service.LikeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LikeNationController {
    private final LikeNationServiceImpl likeNationService;
    private final UserRepository userRepository;
    @PostMapping("/nation/{nationId}/like")
    public ResponseEntity<HttpStatus> LikeNation(@PathVariable Long nationId, @AuthenticationPrincipal CustomUserDetail customUserDetail){
        try {
            User user = customUserDetail.getUser();
            likeNationService.updateOfLikeNation(nationId,user);
            ResponseEntity
                    .status(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
