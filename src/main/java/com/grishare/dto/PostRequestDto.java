package com.grishare.dto;

import com.grishare.domain.Post;
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

    public Post toEntity() {
        Post post = new Post(
                this.title,
                this.content
        );
        return post;
    }
}