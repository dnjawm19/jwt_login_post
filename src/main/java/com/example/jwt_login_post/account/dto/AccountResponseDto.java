package com.example.jwt_login_post.account.dto;

import com.example.jwt_login_post.account.entity.Account;
import com.example.jwt_login_post.account.entity.Comment;
import com.example.jwt_login_post.account.entity.Likes;
import com.example.jwt_login_post.account.entity.Post;
import lombok.Getter;

import java.util.List;

@Getter
public class AccountResponseDto {
    private String name;
    private String email;
    private List<Post> post;
    private List<Comment> comment;
    private List<Likes> likes;

    public AccountResponseDto(Account account) {
        this.name = account.getName();
        this.email = account.getEmail();
        this.post = account.getPost();
        this.comment = account.getComment();
        this.likes = account.getLikes();
    }
}
