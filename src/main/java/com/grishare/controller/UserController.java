package com.grishare.controller;

import com.grishare.base.BaseResponse;
import com.grishare.domain.user.CustomUserDetail;
import com.grishare.domain.user.User;
import com.grishare.dto.*;
import com.grishare.service.MailServiceImpl;
import com.grishare.service.PostServiceImpl;
import com.grishare.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserServiceImpl userService;
    private final   MailServiceImpl mailService;
    private final PostServiceImpl postService;
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    // 회원가입
    @PostMapping(value = "/user/register")
    public ResponseEntity<User> register(@RequestBody RegisterRequestDto registerRequestDto) {
        registerRequestDto.setPassword(passwordEncoder.encode(registerRequestDto.getPassword()));

        User user = userService.saveUser(registerRequestDto);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // 로그인
    @PostMapping("/user/login")
    public ResponseEntity<?> login(HttpServletRequest request, HttpServletResponse response,
                                   @RequestBody LoginRequestDto loginRequestDto) {
        UserDetails userDetails = userService.loadUserByUsername(loginRequestDto.getUserLoginId());
        // 인증 객체 생성
        Authentication authentication
                = new UsernamePasswordAuthenticationToken(userDetails, loginRequestDto.getPassword(), new ArrayList<>());
        try {
            authenticationManager.authenticate(authentication);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid Id or password");
        }
        // SecurityContextHolder : Authentication을 감싸는 객체
        SecurityContextHolder.getContext().setAuthentication(authentication);
        HttpSession session = request.getSession();
        session.setAttribute
                (HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                        SecurityContextHolder.getContext());
        Cookie cookie = new Cookie("JSESSIONID", session.getId());
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setDomain("localhost");
        cookie.setMaxAge(30000 * 60);
        response.addCookie(cookie);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 회원정보 수정
    @PutMapping("/myPage")
    public ResponseEntity<UserReturnDto> updateUser(@AuthenticationPrincipal CustomUserDetail customUserDetail, @RequestBody UserRequestDto userRequestDto) {
        User user = userService.updateUser(customUserDetail.getUser(), userRequestDto);
        UserReturnDto userReturn = userService.getUser(user);

        return ResponseEntity.ok(userReturn);
    }


    // 비밀번호 찾기 -> 이메일 보내기
    @ResponseBody
    @PostMapping("/user/findPw")
    public ResponseEntity<MailDto> sendPwdEmail(@RequestBody MailRequestDto mailRequestDto) {

        String memberEmail = mailRequestDto.getEmail();
        System.out.println("memberEmail = " + memberEmail);
        // 임시 비밀번호 생성
        String tmpPassword = userService.getTmpPassword();
        // 임시 비밀번호 저장
        userService.updatePassword(tmpPassword, memberEmail);
        // 메일 생성, 전송
        MailDto mail = mailService.createMail(tmpPassword, memberEmail);
        mailService.sendMail(mail);

        return ResponseEntity.ok(mail);


    }
    // 내가 쓴 글 목록 전체 보기
    @GetMapping("/myPost")
    public ResponseEntity<List<PostReturnDto>> getMyPosts (@AuthenticationPrincipal CustomUserDetail customUserDetail){
        List<PostReturnDto> myPostList = userService.getMyPost(customUserDetail.getUser().getId());
        return ResponseEntity.status(HttpStatus.OK).body(myPostList);
    }
    // 스크랩한 글 조회
    @GetMapping("/posts/scrap")
    public ResponseEntity<List<PostReturnDto>> getScrapPosts(@AuthenticationPrincipal CustomUserDetail customuserDetail) {
        List<PostReturnDto> scrapPostList = userService.getMyScrap(customuserDetail.getUser().getId());

        return ResponseEntity.status(HttpStatus.OK).body(scrapPostList);
    }
}