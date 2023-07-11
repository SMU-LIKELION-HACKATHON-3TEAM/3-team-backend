package com.grishare.dto;

import com.grishare.domain.Nation;
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

    public Post toEntity(Nation nation) {
        Post post = new Post(
                this.title,
                this.content,
                nation
        );

        return post;
    }
}
//                this.writer