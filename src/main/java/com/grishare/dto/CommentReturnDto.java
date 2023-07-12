package com.grishare.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.grishare.domain.Comment;
import com.grishare.domain.Post;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentReturnDto {
    private Long commentId;
    private String content;
    private String writer;
    private String writerImg;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<CommentReturnDto> childCommentList;

    public CommentReturnDto(Comment comment) {
        this.commentId = comment.getId();
        this.content = comment.getContent();
        this.writerImg = comment.getUser().getPicture();
        this.writer = comment.getUser().getNickName(); // 글쓰기 닉네임표시
    }
}
