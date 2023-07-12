package com.grishare.dto;

import com.grishare.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.format.DateTimeFormatter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostReturnDto {
    private Long id;
    private String title;
    private String contents;

    private String writer;
    private String userId;
    private String createAt;
    private long views;

    public PostReturnDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.contents = post.getContent();
        this.writer = post.getUser().getNickName(); // 글쓰기 닉네임표시
        this.userId = post.getUser().getUserLoginId(); // 유저 정보 조회
        this.createAt = post.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.views = post.getView();
    }
}
