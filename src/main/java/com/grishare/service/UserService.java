package com.grishare.service;

import com.grishare.domain.user.User;
import com.grishare.dto.*;

import javax.mail.internet.MimeMessage;
import java.util.List;

public interface UserService {

    public User saveUser(RegisterRequestDto registerRequestDto);
    public UserReturnDto getUser(User user); // 회원정보 가져오기
    public User updateUser(User user, UserRequestDto userRequestDto); // 회원정보 수정
//    public User getPwd(User user, PwdRequestDto pwdRequestDto); // 비밀번호 가져오기

    public String getTmpPassword();
    public void updatePassword(String tmpPassword, String memberEmail);

    public List<PostSimpleDto> getMyScrap(Long userId);
    public List<PostReturnDto> getMyPost(Long userId);







}
