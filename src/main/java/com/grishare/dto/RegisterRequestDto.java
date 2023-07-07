package com.grishare.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
public class RegisterRequestDto {
    private String userId;
    private String password;
    private String email;
    private String userName;
    private String NickName;
    private Long birthDay;

    public RegisterRequestDto(String userId, String password, String email, String userName, String NickName, Long birthDay) {
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.userName = userName;
        this.NickName = NickName;
        this.birthDay = birthDay;
    }
}
