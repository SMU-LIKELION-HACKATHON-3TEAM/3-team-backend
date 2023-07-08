package com.grishare.service;

import com.grishare.domain.user.CustomUserDetail;
import com.grishare.domain.user.User;
import com.grishare.dto.PwdRequestDto;
import com.grishare.dto.RegisterRequestDto;
import com.grishare.dto.UserReturnDto;
import com.grishare.repository.UserRepository;
import com.grishare.service.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements UserDetailsService , UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(@Lazy UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = userRepository.findByUserId(userId).orElse(null);

        if (user == null){
            throw new UsernameNotFoundException("UsernameNotFoundException");
        }

        return new CustomUserDetail(user);

    }

    @Override
    public User saveUser(RegisterRequestDto registerRequestDto){
        User user = User.builder()
                .email(registerRequestDto.getEmail())
                .password(registerRequestDto.getPassword())
                .userId(registerRequestDto.getUserId())
                .birthDay(registerRequestDto.getBirthDay())
                .nickName(registerRequestDto.getNickName())
                .build();
        UserReturnDto userReturnDto = new UserReturnDto(user); // userReturnDto 수정 필ㅑ

        return userRepository.save(user);
    }
//    @Override
//    @Transactional
//    public User getPwd(User user, PwdRequestDto pwdRequestDto);{    // 비밀번호 가져오기
//        Optional
// }
//    @Override
//    @Transactional
//    public UserReturnDto getUser(User user);{   // 회원정보 가져오기
//        Optional<User> byId = userRepository.findById(user.getUserId);
//
//        UserReturnDto userReturnDto = UserReturnDto.builder()
//                .build();
//
//        return userReturnDto;
//
//    }
//    public User updateUser(User user, UserReturnDto userReturnDto);{    // 회원정보 수정
//
//    }

}
