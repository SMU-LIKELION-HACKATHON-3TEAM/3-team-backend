package service;

import com.grishare.domain.user.User;
import com.grishare.dto.PwdRequestDto;
import com.grishare.dto.RegisterRequestDto;
import com.grishare.dto.UserReturnDto;
import com.grishare.repository.UserRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface UserService {


    public User saveUser(RegisterRequestDto registerRequestDto);
//    public UserReturnDto getUser(User user); // 회원정보 가져오기
//    public User updateUser(User user, UserReturnDto userReturnDto); // 회원정보 수정
//    public User getPwd(User user, PwdRequestDto pwdRequestDto); // 비밀번호 가져오기



}
