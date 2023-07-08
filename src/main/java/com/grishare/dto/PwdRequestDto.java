package com.grishare.dto;

import lombok.Data;

@Data
public class PwdRequestDto {    // 비밀번호 찾기 -> 이메일로 임시 비밀번호 전송..?

    private String userId;
    private String email;
}
