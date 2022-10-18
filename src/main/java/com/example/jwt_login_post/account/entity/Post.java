package com.example.jwt_login_post.account.entity;

import com.example.jwt_login_post.account.dto.PostDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class Post extends Timestamped {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "postId")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String userEmail;

    @Column(nullable = false)
    private String contents;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
            @JsonIgnore
    List<Likes> likes = new ArrayList<>();

    // 좋아요의 수
    private Integer countLike;

    public Post(PostDto requestDto,String userEmail) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.userEmail = userEmail;
    }

    public void update(PostDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }

    public void updateLikeCount(int countLike) {
        this.countLike = countLike;
    }
}