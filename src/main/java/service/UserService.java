package service;

import com.grishare.domain.user.User;
import com.grishare.dto.RegisterRequestDto;

public interface UserService {
    public User saveUser(RegisterRequestDto registerRequestDto);

}
