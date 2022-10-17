package com.example.jwt_login_post.account.dto;

import lombok.Getter;

@Getter
public class PostDto {
    private String title;
    private String userEmail;
    private String contents;
    private String nickname;
}