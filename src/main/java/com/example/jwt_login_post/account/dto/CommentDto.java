package com.example.jwt_login_post.account.dto;

import lombok.Getter;

@Getter
public class CommentDto {
    private Long postId;
    private String userEmail;
    private String contents;
}