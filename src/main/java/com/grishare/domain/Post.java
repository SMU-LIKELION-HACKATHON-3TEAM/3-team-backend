package com.grishare.domain;

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
    @ManyToOne
    @JoinColumn(name = "nationId")
    private Nation nation;


    public Post(String title, String content, Nation nation) {
        this.title = title;
        this.content = content;
        this.nation = nation;
    }

}
