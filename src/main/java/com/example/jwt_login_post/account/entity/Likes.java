package com.example.jwt_login_post.account.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "post_id")
    private Post post;

//    @ManyToOne
//    @JsonIgnore
//    @JoinColumn(name = "member_id")
//    private Account account;

    public Likes(Post post, String email){
        this.post = post;
        this.email = email;
    }
}