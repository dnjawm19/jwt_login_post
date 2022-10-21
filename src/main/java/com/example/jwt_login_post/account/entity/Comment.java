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

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "account_id")
    private Account account;


    public Comment(Account account, CommentDto requestDto, Post post) {
        this.userEmail = account.getEmail();
        this.post = post;
        this.contents = requestDto.getContents();
        this.account = account;
    }

    public void update(CommentDto requestDto) {
        this.contents = requestDto.getContents();
    }
}