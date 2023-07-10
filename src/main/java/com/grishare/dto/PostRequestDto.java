package com.grishare.dto;

import com.grishare.domain.Post;
import com.grishare.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostRequestDto {
    private String title;
    private String content;
    private User writer;

    public Post toEntity() {
        Post post = new Post(
                this.title,
                this.content,
                this.writer
        );

        return post;
    }
}
//                this.writer