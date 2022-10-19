package com.example.jwt_login_post.account.controller;

import com.example.jwt_login_post.account.dto.PostDto;
import com.example.jwt_login_post.account.dto.PostResponseDto;
import com.example.jwt_login_post.account.entity.Post;
import com.example.jwt_login_post.account.repository.PostRepository;
import com.example.jwt_login_post.account.service.PostService;
import com.example.jwt_login_post.jwt.util.JwtUtil;
import com.example.jwt_login_post.security.user.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {
    private final PostService postService;

    @PostMapping("")            //게시글 작성
    public Post createPost(@RequestBody PostDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.create(requestDto, userDetails);
    }

    @GetMapping("/get")        //게시글 전체 조회
    public List<Post> getPost() {
        return postService.getAll();
    }

    @GetMapping("/get/{postId}")    //게시글 상세 조회
    public PostResponseDto getPostId(@PathVariable Long postId) {
        return postService.detail(postId);
    }

    @PutMapping("/{postId}")    //게시글 수정
    public String updatePost(@PathVariable Long postId, @RequestBody PostDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.update(postId, requestDto, userDetails);
    }

    @DeleteMapping("/{postId}")      //게시글 삭제
    public String deletePost(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.deletePost(postId, userDetails);
    }


}
