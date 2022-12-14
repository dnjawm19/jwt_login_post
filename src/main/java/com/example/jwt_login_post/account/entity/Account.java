package com.example.jwt_login_post.account.entity;

import com.example.jwt_login_post.account.dto.AccountReqDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;
    @NotBlank
    private String email;
    @NotBlank
    @JsonIgnore
    private String password;
    @NotBlank
    private String phoneNumber;

    @OneToMany(mappedBy = "account")
    List<Post> post = new ArrayList<>();

    @OneToMany(mappedBy = "account")
    List<Comment> comment = new ArrayList<>();

    @OneToMany(mappedBy = "account")
    List<Likes> likes = new ArrayList<>();

    public Account(AccountReqDto accountReqDto) {
        this.name = accountReqDto.getName();
        this.email = accountReqDto.getEmail();
        this.password = accountReqDto.getPassword();
        this.phoneNumber = accountReqDto.getPhoneNumber();
    }

}