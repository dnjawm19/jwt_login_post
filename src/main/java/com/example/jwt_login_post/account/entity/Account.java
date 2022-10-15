package com.example.jwt_login_post.account.entity;

import com.example.jwt_login_post.account.dto.AccountReqDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Getter
@Entity
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String phoneNumber;

    public Account(AccountReqDto accountReqDto) {
        this.email = accountReqDto.getEmail();
        this.password = accountReqDto.getPassword();
        this.phoneNumber = accountReqDto.getPhoneNumber();
    }

}