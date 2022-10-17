package com.example.jwt_login_post.account.repository;

import com.example.jwt_login_post.account.entity.Likes;
import com.example.jwt_login_post.account.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikesRepository extends JpaRepository<Likes, Long> {
//    List<Likes> findByUserEmailAndPost(String email, Long post);
//
    Boolean existsByPostAndEmail(Post post, String email);
}
