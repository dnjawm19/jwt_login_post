package com.example.jwt_login_post.account.entity;

import com.example.jwt_login_post.account.dto.PostDto;
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

    @OneToMany(mappedBy = "post")
    List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "post")
    List<Likes> likes = new ArrayList<>();

    private Integer countLike = likes.size();

    public Post(PostDto requestDto,String userEmail) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.userEmail = userEmail;
    }

    public void update(PostDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }
}