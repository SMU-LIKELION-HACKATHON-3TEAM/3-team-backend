package com.grishare.controller;

import com.grishare.domain.user.User;
import com.grishare.dto.RegisterRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.grishare.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping(value="/user/register")
    public ResponseEntity<User> register(@RequestBody RegisterRequestDto registerRequestDto){
        registerRequestDto.setPassword(passwordEncoder.encode(registerRequestDto.getPassword()));

        User user = userService.saveUser(registerRequestDto);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

//    @PostMapping("/user/login")
//    public Re
}
