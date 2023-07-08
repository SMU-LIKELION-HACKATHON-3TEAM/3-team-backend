package com.grishare.domain.user;

import com.grishare.dto.RegisterRequestDto;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name="users")
@Getter @Builder @ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //사용자 PK -> id
    @Column(name="user_id")
    private String userId; // 사용자가 입력하는 ID
    @Column(unique = true)
    private String email;
    private String password;
    @Column(unique = true)
    private String nickName; // 닉네임
    private String userName;    // 사용자 이름
    @CreatedDate
    private LocalDateTime createdAt; // 가입날짜
    private Long birthDay; // 회원 생년월일 erd에 없음
    private String picture; // 프로필 이미지 -> 배포여부에 따라 -> 일단 배포는 미정 안할
    // 이미지 경로를 저장하는 변수
    private String address;
    //@Embedded
    //private NotificationSetting notificationSetting; // 알림 설정

    public User encodePassword(PasswordEncoder passwordEncoder){
        password = passwordEncoder.encode(password);
        return this;
    }

    public  void updatePassword(PasswordEncoder passwordEncoder, String password){
        this.password = passwordEncoder.encode(password);
    }

    public boolean matchPassword(PasswordEncoder passwordEncoder, String checkPassword){
        return passwordEncoder.matches(checkPassword, getPassword());
    }
//    public User(String userId, String email, String password, String nickName, String userName, Integer birthDay) {
//        this.userId = userId;
//        this.email = email;
//        this.password = password;
//        this.nickName = nickName;
//        this.userName = userName;
//        this.birthDay = birthDay;
//    }


}
