package com.grishare.dto;

import com.grishare.domain.Post;
import com.grishare.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostReturnDto {
    private Long id;
    private String title;
    private String content;

    private String writer;
    private String userId;

    public PostReturnDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.writer = post.getUser().getNickName(); // 글쓰기 닉네임표시
        this.userId = post.getUser().getUserId(); // 유저 정보 조회
    }

}
