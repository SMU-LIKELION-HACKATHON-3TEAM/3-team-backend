package com.grishare.service;

import com.grishare.domain.Post;
import com.grishare.domain.Scrap;
import com.grishare.domain.user.CustomUserDetail;
import com.grishare.domain.user.User;
import com.grishare.dto.*;
import com.grishare.repository.PostRepository;
import com.grishare.repository.ScrapRepository;
import com.grishare.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserDetailsService , UserService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final ScrapRepository scrapRepository;
    private final PasswordEncoder passwordEncoder;

    private final JavaMailSender javaMailSender;


    public UserServiceImpl(@Lazy UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder, @Lazy ScrapRepository scrapRepository, @Lazy JavaMailSender javaMailSender, @Lazy PostRepository postRepository){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.javaMailSender = javaMailSender;
        this.postRepository = postRepository;
        this.scrapRepository = scrapRepository;
    }

    // spring security 관련 UserDetailsService implement 부분
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = userRepository.findByUserLoginId(userId).orElse(null);

        if (user == null){
            throw new UsernameNotFoundException("UsernameNotFoundException");
        }

        return new CustomUserDetail(user);

    }
    // 회원가입
    @Override
    public User saveUser(RegisterRequestDto registerRequestDto){
        User user = User.builder()
                .userName(registerRequestDto.getUserName())
                .email(registerRequestDto.getEmail())
                .password(registerRequestDto.getPassword())
                .userLoginId(registerRequestDto.getUserLoginId())
                .birthDay(registerRequestDto.getBirthDay())
                .nickName(registerRequestDto.getNickName())
                .build();
        UserReturnDto userReturnDto = new UserReturnDto(user); // userReturnDto 수정 필ㅑ

        return userRepository.save(user);
    }

    // 회원정보 가져오기
    // 기본 정보 + 관심국가 + 내가 쓴 리뷰(부분) + 스크랩 게시물(부분)
    @Override
    @Transactional
    public UserReturnDto getUser(User user){
        Optional<User> byId = userRepository.findById(user.getId()); // pk값(id) 가져옴
        User me = byId.orElseThrow(() -> new IllegalArgumentException("user doesn't exist"));

        UserReturnDto userReturnDto = UserReturnDto.builder()
                .email(me.getEmail())
                .nickName(me.getNickName())
                .userImg(me.getUserImg())
                .password(me.getPassword())
                .backgroundImg(me.getBackgroundImg())
                .build();
//        if (category.equals("nationLike")){ // 관심 국가 설정은 Post에서 좋아요?
//        List<NationLike> nationLikes = nationLikeRepository.findAllByuserId(userId);


        return userReturnDto;

    }
    // 회원정보 수정 -> 이미지 어떻게 할 것인가.
    @Override
    @Transactional
    public User updateUser(User user, UserRequestDto userRequestDto){

        Optional<User> byId = userRepository.findById(user.getId()); // pk값(id) 가져옴
        User me = byId.orElseThrow(() -> new IllegalArgumentException("user doesn't exist"));
        String encryptPassword = passwordEncoder.encode(userRequestDto.getPassword());
        me.setNickName(userRequestDto.getNickName());
        me.setUserImg(userRequestDto.getUserImg());
        me.setBackgroundImg(userRequestDto.getBackgroundImg());
        me.setUserLoginId(userRequestDto.getUserLoginId());

        userRepository.save(me);
        // 비밀번호 변경 -> passwordEncoder 적용
        me.updatePassword(encryptPassword);

        return me;

    }
//    public String getCategory(Long postId){
//        Post post = postRepository.findById(postId).get();
////        return post.getCategory().toString().toLowerCase();
//    }
    // 내가 쓴 리뷰 전체 조회
    @Override
    public List<PostReturnDto> getMyPost(Long userId){ // userId에 로그인한 회원 Pk가 들어가야 됨

       List<Post> myPosts = postRepository.findAllByUserId(userId);

        return myPosts.stream().map(PostReturnDto::new).collect(Collectors.toList());
    }

    // 스크랩한 글 전체 조회
    @Override
    public List<PostReturnDto> getMyScrap(Long userId){ // userId에 로그인한 회원 Id가 들어가야 됨
        List<PostReturnDto> myScrapList = scrapRepository.findAllByUserId(userId).stream()
                                    .map(scrap -> new PostReturnDto(scrap.getPost()))
                                    .collect(Collectors.toList());
        return myScrapList;
    }

    // 메일 보내기
    // 임시 비밀번호 생성
    @Override
    public String getTmpPassword() {
        char[] charSet = new char[]{ '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
                'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

        String pwd = "";

        /* 문자 배열 길이의 값을 랜덤으로 10개를 뽑아 조합 */
        int idx = 0;
        for(int i = 0; i < 10; i++){
            idx = (int) (charSet.length * Math.random());
            pwd += charSet[idx];
        }

        log.info("임시 비밀번호 생성");

        return pwd;
    }

    // 임시 비밀번호로 업데이에
    @Override
    public void updatePassword(String tmpPassword, String memberEmail) {

        String encryptPassword = passwordEncoder.encode(tmpPassword);
        User user = userRepository.findByEmail(memberEmail).orElseThrow(() ->
                new IllegalArgumentException("해당 사용자가 존재하지 않습니다."));

        user.updatePassword(encryptPassword);
        log.info("임시 비밀번호 업데이트");
    }





}
