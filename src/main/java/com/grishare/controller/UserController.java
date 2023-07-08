package com.grishare.controller;

import com.grishare.domain.user.User;
import com.grishare.dto.LoginRequestDto;
import com.grishare.dto.RegisterRequestDto;
import com.grishare.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;
import com.grishare.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    @PostMapping(value="/user/register")
    public ResponseEntity<User> register(@RequestBody RegisterRequestDto registerRequestDto){
        registerRequestDto.setPassword(passwordEncoder.encode(registerRequestDto.getPassword()));

        User user = userService.saveUser(registerRequestDto);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/user/login")
    public ResponseEntity<?> login(HttpServletRequest request, HttpServletResponse response,
                                   @RequestBody LoginRequestDto loginRequestDto) {
        UserDetails userDetails = userService.loadUserByUsername(loginRequestDto.getUserId());
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
        Cookie cookie = new Cookie("JSESSIONID",session.getId());
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(30000*60);
        cookie.setSecure(true);
        response.addCookie(cookie);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
