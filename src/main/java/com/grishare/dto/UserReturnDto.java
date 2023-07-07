package com.grishare.dto;

import com.grishare.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserReturnDto {

    public UserReturnDto(User user){

    }
}
