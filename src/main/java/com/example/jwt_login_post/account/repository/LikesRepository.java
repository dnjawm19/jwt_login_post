package com.example.jwt_login_post.account.repository;

import com.example.jwt_login_post.account.entity.Account;
import com.example.jwt_login_post.account.entity.Likes;
import com.example.jwt_login_post.account.entity.Post;
import com.example.jwt_login_post.security.user.UserDetailsImpl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikesRepository extends JpaRepository<Likes, Long> {
//    List<Likes> findByUserEmailAndPost(String email, Long post);
//
    Boolean existsByPostAndAccount(Post post, Account account);
    void deleteByPostAndAccount(Post post, Account account);
}
