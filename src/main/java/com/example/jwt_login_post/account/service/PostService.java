package com.example.jwt_login_post.account.service;

import com.example.jwt_login_post.account.dto.PostDto;
import com.example.jwt_login_post.account.entity.Post;
import com.example.jwt_login_post.account.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public Long update(Long id, PostDto requestDto, String email) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        String p = post.getUserEmail();
        if(p.equals(email)){
            post.update(requestDto);
            return post.getId();
        }

        return id;


    }
}
