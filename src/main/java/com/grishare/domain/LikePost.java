package com.grishare.domain;

import com.grishare.domain.user.User;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Data
@Builder
@Entity
public class LikePost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(nullable = false)
    private boolean status; // true = 좋아요, false = 좋아요 취소

    public LikePost(Post post, User user){
        this.post = post;
        this.user = user;
        this.status = true;
    }

}
