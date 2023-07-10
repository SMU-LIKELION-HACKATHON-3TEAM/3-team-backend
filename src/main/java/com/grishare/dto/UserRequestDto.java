package com.grishare.dto;

import com.grishare.domain.user.User;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
public class UserRequestDto {

    // 회원 수정 관련 requestDto -> 비밀번호, 닉네임, 사진을 수정할 수 있다!
    private String password;
    private String nickName;
    private String picture;
    private String userId;

    public UserRequestDto(User user) {
        this.password = password;
        this.nickName = nickName;
        this.picture = picture;
        this.userId = userId;
    }
}

