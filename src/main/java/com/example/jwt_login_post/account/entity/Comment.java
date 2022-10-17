package com.example.jwt_login_post.account.entity;

import com.example.jwt_login_post.account.dto.CommentDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Comment extends Timestamped {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String userEmail;

    @Column(nullable = false)
    private String contents;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "post_id")
    private Post post;


    public Comment(String email, CommentDto requestDto, Post post) {
        this.userEmail = email;
        this.post = post;
        this.contents = requestDto.getContents();
    }

    public void update(CommentDto requestDto) {
        this.contents = requestDto.getContents();
    }
}