package com.example.jwt_login_post.account.controller;

import com.example.jwt_login_post.account.dto.PostDto;
import com.example.jwt_login_post.account.entity.Post;
import com.example.jwt_login_post.account.repository.PostRepository;
import com.example.jwt_login_post.account.service.PostService;
import com.example.jwt_login_post.jwt.dto.TokenDto;
import com.example.jwt_login_post.jwt.util.JwtUtil;
import com.example.jwt_login_post.security.user.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {
    private final PostRepository postRepository;
    private final PostService postService;

    private final JwtUtil jwtUtil;

    @PostMapping("")            //게시글 작성
    public Post createPost(@RequestBody PostDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.create(requestDto, userDetails);
    }

    @GetMapping("/get")        //게시글 전체 조회
    public List<Post> getPost() {
        return postService.getAll();
    }

    @GetMapping("/get/{postId}")    //게시글 상세 조회
    public Post getPostId(@PathVariable Long postId) {
        return postService.detail(postId);
    }

    @PutMapping("/{postId}")    //게시글 수정
    public Long updatePost(@PathVariable Long postId, @RequestBody PostDto requestDto, @RequestHeader("ACCESS_TOKEN") String token) {
        String email = jwtUtil.getEmailFromToken(token);
        postService.update(postId, requestDto, email);
        return postId;
    }

    @DeleteMapping("/{postId}")      //게시글 삭제
    public Long deletePost(@PathVariable Long postId) {
        postRepository.deleteById(postId);
        return postId;
    }


}
