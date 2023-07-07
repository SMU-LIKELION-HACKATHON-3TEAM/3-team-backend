package service;

import com.grishare.domain.user.User;
import com.grishare.domain.user.UserRole;
import com.grishare.dto.RegisterRequestDto;
import com.grishare.dto.UserReturnDto;
import com.grishare.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(@Lazy UserRepository userRepository,@Lazy PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

    }


    @Autowired
    public User saveUser(RegisterRequestDto registerRequestDto){
        User user = User.builder()
                .email(registerRequestDto.getEmail())
                .password(registerRequestDto.getPassword())
                .userId(registerRequestDto.getUserId())
                .birthDay(registerRequestDto.getBirthDay())
                .nickName(registerRequestDto.getNickName())
                .role(UserRole.ROLE_USER)
                .build();
        UserReturnDto userReturnDto = new UserReturnDto(user); // userReturnDto 수정 필요

        return userRepository.save(user);
    }
}
