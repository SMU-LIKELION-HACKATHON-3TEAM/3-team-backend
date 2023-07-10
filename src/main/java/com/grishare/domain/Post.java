package com.grishare.domain;

import com.grishare.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "post")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)


public class Post {
    @Id //pk
    @GeneratedValue(strategy = GenerationType.AUTO) // table id 생성 규칙
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "contents")
    private String content;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime modifiedAt;
    @Column(name = "views")
    private Long view;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @OneToMany(mappedBy = "post",orphanRemoval = true)
    private List<Comment> comments;
    @OneToMany(mappedBy = "post",orphanRemoval = true)
    private List<Scrap> scraps;
    // 카테고리 , 작성자 , 좋아요 , 댓글, 이미지 관련 변수 필요함
    public Post(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
    }

}
