package com.example.jwt_login_post.account.service;

import com.example.jwt_login_post.account.entity.Likes;
import com.example.jwt_login_post.account.entity.Post;
import com.example.jwt_login_post.account.repository.AccountRepository;
import com.example.jwt_login_post.account.repository.LikesRepository;
import com.example.jwt_login_post.account.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LikesService {
    private final PostRepository postRepository;
    private final AccountRepository accountRepository;
    private final LikesRepository likesRepository;


    @Transactional
    public Likes createLike(Long postId, String email) {
        // requestDto에 있는 postId를 이용해서 post를 들고옵니다. (postRepository를 사용)
        // a게시글을 들고옴
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("포스트 없음"));

//        Account account = accountRepository.findByEmail(email)
//                .orElseThrow(() -> new IllegalArgumentException("이메일 존재하지 않음"));

        Likes likes = new Likes(post, email);
//        Boolean bool = likesRepository.existsByPostAndEmail(postId, email);
        if(!likesRepository.existsByPostAndEmail(post, email)){
            likesRepository.save(likes);
            return likes;
        }
        return likes;
    }
}