package com.grishare.dto;

import com.grishare.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostSimpleDto {

    private Long id;
    private String title;
    private String nickName;
    private int liked;
    private int scraped;
    // 뭔가 더 추가해야 될 것 같음

    public static PostSimpleDto toDto(Post post){
        return new PostSimpleDto(post.getId(), post.getTitle(), post.getUser().getNickName(),post.getLiked(),
                post.getScraped());
    }
}
